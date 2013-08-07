/*------------------------------------------------------------------------------
 * PACKAGE : com.freeware.anttasks
 * FILE    : ManifestReader.java
 * CREATED : Mar 2, 2006 12:40:53 PM
 *------------------------------------------------------------------------------
 * ChangeLog:
 *-----------------------------------------------------------------------------*/
package com.freeware.anttasks;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Manifest.Attribute;
import org.apache.tools.ant.taskdefs.Manifest.Section;

/**
 * An ant task for printing the manifest from a file. This task is configured in
 * manifest as shown below.
 * <pre>
 * &lt;target name="printDetails"&gt;
 *     &lt;manifestreader srcfile="${basedir}/myproduct.jar"&gt;
 *         &lt;attribute name="Built-Date" value="Built On"/&gt;
 *         &lt;section name="com/freeware"&gt;
 *             &lt;attribute name="Implementation-Title" value="Product Name"/&gt;
 *             &lt;attribute name="Implementation-Version" value="Product Version"/&gt;
 *             &lt;attribute name="Implementation-Vendor"  value="Copyright"/&gt;
 *         &lt;/section&gt;
 *     &lt;/manifestreader&gt;
 * &lt;/target&gt;
 * </pre>
 * In order to use this task you must instruct ant about this task this can be 
 * done as shown below.
 * <pre>
 * &lt;typedef resource="com/freeware/anttasks/antlib.xml"&gt;
 *     &lt;classpath&gt;
 *         &lt;pathelement location="${ant.contrib}/antfreeware-1.0.0.jar" /&gt;
 *     &lt;/classpath&gt;
 * &lt;/typedef&gt;
 * </pre> 
 * @author Prasad P. Khandekar
 * @version 1.0.0
 */
public class ManifestReader 
	extends Task
{
	
    private String propertyName;
    
    private String sectionName="mySection";
    
	/**
	 * the name of the jar file from which information needs to be extracted
	 */
	private String mstrSrcFile = null;

	/**
	 * A list containing attributes from main section of manifest
	 */
	private Map mainAttrMap = null;

	/**
	 * A list containing various sections defined in manifest
	 */
	private Map sectionMap = null;

	public ManifestReader()
	{
		super();
	}

	/**
	 * Sets the name of the jar file from which manifest needs to be read and
	 * printed.
	 * @param jarFile The name of the jar file.
	 */
	public void setSrcfile(String jarFile)
	{
		mstrSrcFile = jarFile;
	}

	/**
	 * Returns the name of the jar file from which manifest needs to be read and
	 * printed.
	 * @return The name of the jar file.
	 */
	public String getSrcfile()
	{
		return mstrSrcFile;
	}

	/**
	 * Method which allows ant to supply our task the details about main manifest 
	 * attributes to be be printed from the jar file. These attributes are 
	 * configured in build.xml as nested elements of our task as shown below.
	 * <pre>
	 * &lt;target name="printDetails"&gt;
	 *     &lt;manifestreader srcfile="${basedir}/myproduct.jar"&gt;
	 *         &lt;attribute name="Built-Date" value="Built On"/&gt;
	 *         &lt;attribute name="Implementation-Title" value="Product Name"/&gt;
	 *         &lt;attribute name="Implementation-Version" value="Product Version"/&gt;
	 *         &lt;attribute name="Implementation-Vendor"  value="Copyright"/&gt;
	 *     &lt;/manifestreader&gt;
	 * &lt;/target&gt;
	 * </pre>
	 * @param attr The instance of {@link org.apache.tools.ant.taskdefs.Manifest.Attribute}
	 * class holding information about the manifest attribute.
	 */
	public void addAttribute(Attribute attr)
	{
		if (null == mainAttrMap)
			mainAttrMap = new HashMap();
		if (attr != null)
			mainAttrMap.put(attr.getKey(), attr);
	}

	/**
	 * Method which allows ant to supply our task details about one ore more 
	 * sections in manifest files from which the attributes are to be printed.
	 * These attributes are configured in build.xml as nested elements of our 
	 * task as shown below.
	 * <pre>
	 * &lt;target name="printDetails"&gt;
	 *     &lt;manifestreader srcfile="${basedir}/myproduct.jar"&gt;
	 *         &lt;attribute name="Built-Date" value="Built On"/&gt;
	 *         &lt;section name="com/freeware"&gt;
	 *             &lt;attribute name="Implementation-Title" value="Product Name"/&gt;
	 *             &lt;attribute name="Implementation-Version" value="Product Version"/&gt;
	 *             &lt;attribute name="Implementation-Vendor"  value="Copyright"/&gt;
	 *         &lt;/section&gt;
	 *     &lt;/manifestreader&gt;
	 * &lt;/target&gt;
	 * </pre>
	 * @param attr The instance of {@link org.apache.tools.ant.taskdefs.Manifest.Attribute}
	 * class holding information about the manifest attribute.
	 */
	public void addSection(Section section)
	{
		if (null == sectionMap)
			sectionMap = new HashMap();
		if (section != null)
			sectionMap.put(section.getName(), section);
	}

	/* (non-Javadoc)
	 * @see org.apache.tools.ant.Task#execute()
	 */
	public void execute() throws BuildException
	{
		Map.Entry entry = null;
		Section section = null;
		JarFile jarFile = null;
		Iterator iter = null;
		Manifest manifest = null;

		if (!checkFile(mstrSrcFile)) return;
		if (null == mainAttrMap && null == sectionMap) return;

		try
		{
			jarFile = new JarFile(mstrSrcFile);
			manifest = jarFile.getManifest();
			if (mainAttrMap != null) 
				printAttributes(mainAttrMap, manifest.getMainAttributes());

			if (sectionMap != null)
			{
				iter = sectionMap.entrySet().iterator();
				while (iter.hasNext())
				{
					entry = (Map.Entry) iter.next();
					section = (Section) entry.getValue();
					getProject().setProperty(propertyName, returnImplVersion(manifest));
					printAttributes(section, manifest.getAttributes(section.getName()));
				}
			}

		}
		catch (IOException Ex)
		{
		}
		finally
		{
			if (manifest != null) manifest = null;
			try
			{
				if (jarFile != null)
					jarFile.close();
			}
			catch (IOException ex)
			{
			}
			jarFile = null;
		}
	}
	
	private String returnImplVersion(Manifest manifest){
		Map attrsMap = manifest.getAttributes(sectionName);
		if(attrsMap != null)
			return (String) attrsMap.get(new Attributes.Name("Implementation-Version"));
		else
			return "";
		
	}

	/**
	 * helper method to determines whether the jar file from which manifest is 
	 * to be read exists and is a valid jar file.
	 * @param pstrFile The jar file path and name.
	 * @return true if jar file exists and is a valid jar, false otherwise
	 */
	private boolean checkFile(String pstrFile)
	{
		boolean blnRet = false;
		File file = null;
		JarFile jarFile = null;

		if (null == pstrFile) return blnRet;

		file = new File(pstrFile);
		blnRet = file.exists();
		try
		{
			if (blnRet)
			{
				blnRet = false;
				jarFile = new JarFile(file);
				jarFile.close();
				blnRet = true;
			}
		}
		catch (IOException Ex)
		{
			blnRet = false;
		}
		finally
		{
			try
			{
				if (jarFile != null)
					jarFile.close();
			}
			catch (IOException Ex)
			{
			}
			if (file != null) file = null;
		}
		return blnRet;
	}

	/**
	 * Helper method to enumerate the attribute map and print corrosponding 
	 * attribute values from manifest file's main attribues
	 * @param infoMap The map of attributes whose value are to be read from manifest
	 * @param attrs The map of attributes from manifest.
	 */
	private void printAttributes(Map infoMap, Map attrs)
	{
		String key = null;
		String attrVal = null;
		Iterator iter = null;
		Map.Entry entry = null;
		Attribute srcAttr = null;

		if (null == infoMap || null == attrs) return;

		iter = infoMap.entrySet().iterator();
		while (iter.hasNext())
		{
			entry = (Map.Entry) iter.next();
			srcAttr = (Attribute) entry.getValue();
			key = srcAttr.getName();
			attrVal = (String) attrs.get(new Attributes.Name(key));
			if (attrVal != null)
				System.out.println(srcAttr.getValue() + " " + attrVal);
		}
	}

	/**
	 * Helper method to enumerate the manifest section and print the attribute 
	 * values.
	 * @param section The manifest section.
	 * @param attrMap The map of attributes from a manifest section.
	 */
	private void printAttributes(Section section, Map attrs)
	{
		String key = null;
		String attrName = null;
		String attrVal = null;
		Attribute srcAttr = null;
		Enumeration enm = null;

		if (null == section || null == attrs) return;

		enm = section.getAttributeKeys();
		while (enm.hasMoreElements())
		{
			key = (String) enm.nextElement();
			srcAttr = section.getAttribute(key);
			attrName = srcAttr.getName();
			attrVal = (String) attrs.get(new Attributes.Name(attrName));
			if (attrVal != null)
				System.out.println(srcAttr.getValue() + " " + attrVal);
		}
	}
	
	 public void setProperty(String propertyName) {
	        this.propertyName = propertyName;
	 }
}
