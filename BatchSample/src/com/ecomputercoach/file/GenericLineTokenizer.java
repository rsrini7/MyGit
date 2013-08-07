package com.ecomputercoach.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.FieldSetFactory;
import org.springframework.batch.item.file.transform.IncorrectLineLengthException;
import org.springframework.batch.item.file.transform.IncorrectTokenCountException;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.batch.item.file.transform.RangeArrayPropertyEditor;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class GenericLineTokenizer implements LineTokenizer {
	
	private Boolean delimitedFlag;

	private Range[] ranges;
	
	private List<Range> rangesNew = new ArrayList<Range> ();
	
	private String formatFile;

	private int maxRange = 0;

	boolean open = false;

	private boolean strict = true;
	
	protected String[] names = new String[0];
	
	protected List<String> namesNew = new ArrayList<String>();

	private FieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
	
	public static final char DEFAULT_QUOTE_CHARACTER = '"';

	
	// the delimiter character used when reading input.
	private char delimiter;

	public static final char DELIMITER_COMMA = ',';
	
	private char quoteCharacter = DEFAULT_QUOTE_CHARACTER;

	private String quoteString;

	public GenericLineTokenizer() {
		this(DELIMITER_COMMA);
	}

	public GenericLineTokenizer(char delimiter) {
		Assert.state(delimiter != DEFAULT_QUOTE_CHARACTER, "[" + DEFAULT_QUOTE_CHARACTER
		        + "] is not allowed as delimiter for tokenizers.");

		this.delimiter = delimiter;
		setQuoteCharacter(DEFAULT_QUOTE_CHARACTER);
	}
	

	public void setDelimitedFlag(Boolean delimitedFlag) {
		this.delimitedFlag = delimitedFlag;
	}

	/**
	 * Setter for the delimiter character.
	 * 
	 * @param delimiter
	 */
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	/**
	 * Public setter for the quoteCharacter. The quote character can be used to extend a field across line endings or to
	 * enclose a String which contains the delimiter. Inside a quoted token the quote character can be used to escape
	 * itself, thus "a""b""c" is tokenized to a"b"c.
	 * 
	 * @param quoteCharacter the quoteCharacter to set
	 * 
	 * @see #DEFAULT_QUOTE_CHARACTER
	 */
	public void setQuoteCharacter(char quoteCharacter) {
		this.quoteCharacter = quoteCharacter;
		this.quoteString = "" + quoteCharacter;
	}

	/**
	 * Factory for {@link FieldSet} instances. Can be injected by clients to
	 * customize the default number and date formats.
	 * 
	 * @param fieldSetFactory the {@link FieldSetFactory} to set
	 */
	public void setFieldSetFactory(FieldSetFactory fieldSetFactory) {
		this.fieldSetFactory = fieldSetFactory;
	}

	/**
	 * Setter for column names. Optional, but if set, then all lines must have
	 * as many or fewer tokens.
	 * 
	 * @param names
	 */
	public void setNames(String[] names) {
		this.names = names;
	}
	
	
	public void setFormatFile(String formatFile) {
		this.formatFile = formatFile;
		getDetailsFromFile();
	}
	
	private void getDetailsFromFile() {
		Assert.notNull(formatFile);
		BufferedReader formatFileReader = null;
		try{
			formatFileReader = new BufferedReader(new FileReader(formatFile));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		String line;
		String delims = ",";
		try{
			int i = 0;
			while((line = formatFileReader.readLine()) != null){
				String tokens[] = line.split(delims);
				
				if(delimitedFlag){
					namesNew.add(tokens[1]);
				}else{
					Range range = new Range(Integer.parseInt(tokens[1])+1,Integer.parseInt(tokens[1])+ Integer.parseInt(tokens[2]) );
					rangesNew.add(range);
					namesNew.add(tokens[3]);
				}
				i++;
			}
			String[] ar1 = namesNew.toArray(new String[i-1]);
			setNames(ar1);
			
			if(!delimitedFlag)
				setColumns(rangesNew.toArray(new Range[i-1]));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * Yields the tokens resulting from the splitting of the supplied
	 * <code>line</code>.
	 * 
	 * @param line the line to be tokenised (can be <code>null</code>)
	 * 
	 * @return the resulting tokens
	 */
	public FieldSet tokenize(String line) {

		if (line == null) {
			line = "";
		}
		List<String> tokens;
		
		if(!delimitedFlag){
			tokens = new ArrayList<String>(doFixedTokenize(line));
		}
		else{
			tokens = new ArrayList<String>(doDelimitedTokenize(line));
		}
		String[] values = (String[]) tokens.toArray(new String[tokens.size()]);
		
		System.out.println("Names Length == "+names.length);

		if (names.length == 0) {
			return fieldSetFactory.create(values);
		}
		else if (values.length != names.length) {
			throw new IncorrectTokenCountException(names.length, values.length);
		}
		return fieldSetFactory.create(values, names);
	}

	/**
	 * Public setter for the strict flag. If true (the default) then lines must
	 * be precisely the length specified by the columns. If false then shorter
	 * lines will be tolerated and padded with empty columns, and longer strings
	 * will simply be truncated.
	 * 
	 * @see #setColumns(Range[])
	 * 
	 * @param strict the strict to set
	 */
	public void setStrict(boolean strict) {
		this.strict = strict;
	}

	/**
	 * Set the column ranges. Used in conjunction with the
	 * {@link RangeArrayPropertyEditor} this property can be set in the form of
	 * a String describing the range boundaries, e.g. "1,4,7" or "1-3,4-6,7" or
	 * "1-2,4-5,7-10". If the last range is open then the rest of the line is
	 * read into that column (irrespective of the strict flag setting).
	 * 
	 * @see #setStrict(boolean)
	 * 
	 * @param ranges the column ranges expected in the input
	 */
	public void setColumns(Range[] ranges) {
		this.ranges = ranges;
		calculateMaxRange(ranges);
	}

	/*
	 * Calculate the highest value within an array of ranges. The ranges aren't
	 * necessarily in order. For example: "5-10, 1-4,11-15". Furthermore, there
	 * isn't always a min and max, such as: "1,4-20, 22"
	 */
	private void calculateMaxRange(Range[] ranges) {
		if (ranges == null || ranges.length == 0) {
			maxRange = 0;
			return;
		}

		open = false;
		maxRange = ranges[0].getMin();

		for (int i = 0; i < ranges.length; i++) {
			int upperBound;
			if (ranges[i].hasMaxValue()) {
				upperBound = ranges[i].getMax();
			}
			else {
				upperBound = ranges[i].getMin();
				if (upperBound > maxRange) {
					open = true;
				}
			}

			if (upperBound > maxRange) {
				maxRange = upperBound;
			}
		}
	}

	/**
	 * Yields the tokens resulting from the splitting of the supplied
	 * <code>line</code>.
	 * 
	 * @param line the line to be tokenised (can be <code>null</code>)
	 * 
	 * @return the resulting tokens (empty if the line is null)
	 * @throws IncorrectLineLengthException if line length is greater than or
	 * less than the max range set.
	 */
	protected List<String> doFixedTokenize(String line) {
		List<String> tokens = new ArrayList<String>(ranges.length);
		int lineLength;
		String token;

		lineLength = line.length();

		if (lineLength < maxRange && strict) {
			throw new IncorrectLineLengthException("Line is shorter than max range " + maxRange, maxRange, lineLength);
		}

		if (!open && lineLength > maxRange && strict) {
			throw new IncorrectLineLengthException("Line is longer than max range " + maxRange, maxRange, lineLength);
		}

		for (int i = 0; i < ranges.length; i++) {

			int startPos = ranges[i].getMin() - 1;
			int endPos = ranges[i].getMax();

			if (lineLength >= endPos) {
				token = line.substring(startPos, endPos);
			}
			else if (lineLength >= startPos) {
				token = line.substring(startPos);
			}
			else {
				token = "";
			}
			tokens.add(token);
		}
		return tokens;
	}
	
	
	/**
	 * Yields the tokens resulting from the splitting of the supplied <code>line</code>.
	 * 
	 * @param line the line to be tokenized
	 * 
	 * @return the resulting tokens
	 */
	protected List<String> doDelimitedTokenize(String line) {

		List<String> tokens = new ArrayList<String>();

		// line is never null in current implementation
		// line is checked in parent: AbstractLineTokenizer.tokenize()
		char[] chars = line.toCharArray();
		boolean inQuoted = false;
		int lastCut = 0;
		int length = chars.length;

		for (int i = 0; i < length; i++) {

			char currentChar = chars[i];
			boolean isEnd = (i == (length - 1));

			if ((isDelimiterCharacter(currentChar) && !inQuoted) || isEnd) {
				int endPosition = (isEnd ? (length - lastCut) : (i - lastCut));

				if (isEnd && isDelimiterCharacter(currentChar)) {
					endPosition--;
				}

				String value = null;

				value = maybeStripQuotes(new String(chars, lastCut, endPosition));

				tokens.add(value);

				if (isEnd && (isDelimiterCharacter(currentChar))) {
					tokens.add("");
				}

				lastCut = i + 1;
			} else if (isQuoteCharacter(currentChar)) {
				inQuoted = !inQuoted;
			}

		}
		return tokens;
	}

	/**
	 * If the string is quoted strip (possibly with whitespace outside the quotes (which will be stripped), replace
	 * escaped quotes inside the string. Quotes are escaped with double instances of the quote character.
	 * 
	 * @param string
	 * @return the same string but stripped and unescaped if necessary
	 */
	private String maybeStripQuotes(String string) {
		String value = string.trim();
		if (isQuoted(value)) {
			value = StringUtils.replace(value, "" + quoteCharacter + quoteCharacter, "" + quoteCharacter);
			int endLength = value.length() - 1;
			// used to deal with empty quoted values
			if (endLength == 0) {
				endLength = 1;
			}
			string = value.substring(1, endLength);
		}
		return string;
	}

	/**
	 * Is this string surrounded by quote characters?
	 * 
	 * @param value
	 * @return true if the value starts and ends with the {@link #quoteCharacter}
	 */
	private boolean isQuoted(String value) {
		if (value.startsWith(quoteString) && value.endsWith(quoteString)) {
			return true;
		}
		return false;
	}

	/**
	 * Is the supplied character the delimiter character?
	 * 
	 * @param c the character to be checked
	 * @return <code>true</code> if the supplied character is the delimiter character
	 * @see DelimitedLineTokenizer#DelimitedLineTokenizer(char)
	 */
	private boolean isDelimiterCharacter(char c) {
		return c == this.delimiter;
	}

	/**
	 * Is the supplied character a quote character?
	 * 
	 * @param c the character to be checked
	 * @return <code>true</code> if the supplied character is an quote character
	 * @see #setQuoteCharacter(char)
	 */
	protected boolean isQuoteCharacter(char c) {
		return c == quoteCharacter;
	}
}
