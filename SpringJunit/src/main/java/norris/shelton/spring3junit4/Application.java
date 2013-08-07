package norris.shelton.spring3junit4;


import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.Date;


/** @author Norris Shelton */
public class Application implements Comparable<Application> {
    public static final String SESSION_KEY = "norris.shelton.directories.applications.Applicatioin";
    
    private int id;
    private String name;
    private String description;
    private String entityIds;
    private String acronym;
    private String aka1;
    private String aka2;
    private String aka3;
    private boolean status;
    private String helpGroup;
    private String version;
    private Date dateUpdated;
    private String vendor;
    private boolean display = true;
    private boolean approved;

    public Application() { }

    public Application(Application application) {
        id = application.getId();
        name = application.getName();
        description = application.getDescription();
        entityIds = application.getEntityIds();
        acronym = application.getAcronym();
        aka1 = application.getAka1();
        aka2 = application.getAka2();
        aka3 = application.getAka3();
        status = application.isStatus();
        helpGroup = application.getHelpGroup();
        version = application.getVersion();
        dateUpdated = application.getDateUpdated();
        vendor = application.getVendor();
        display = application.isDisplay();
        approved = application.isApproved();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(String entityIds) {
        this.entityIds = entityIds;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAka1() {
        return aka1;
    }

    public void setAka1(String aka1) {
        this.aka1 = aka1;
    }

    public String getAka2() {
        return aka2;
    }

    public void setAka2(String aka2) {
        this.aka2 = aka2;
    }

    public String getAka3() {
        return aka3;
    }

    public void setAka3(String aka3) {
        this.aka3 = aka3;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getHelpGroup() {
        return helpGroup;
    }

    public void setHelpGroup(String helpGroup) {
        this.helpGroup = helpGroup;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        Application that = (Application) o;

        if (name != null ? !name.equals(that.name) : that.name != null) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    /**
     * Compares this object with the specified object for order.  Returns a negative integer, zero, or a positive
     * integer as this object is less than, equal to, or greater than the specified object.<p>
     * <p/>
     * In the foregoing description, the notation <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>, <tt>0</tt>, or <tt>1</tt> according to
     * whether the value of <i>expression</i> is negative, zero or positive.
     * <p/>
     * The implementor must ensure <tt>sgn(x.compareTo(y)) == -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and
     * <tt>y</tt>. (This implies that <tt>x.compareTo(y)</tt> must throw an exception iff <tt>y.compareTo(x)</tt> throws
     * an exception.)<p>
     * <p/>
     * The implementor must also ensure that the relation is transitive: <tt>(x.compareTo(y)&gt;0 &amp;&amp;
     * y.compareTo(z)&gt;0)</tt> implies <tt>x.compareTo(z)&gt;0</tt>.<p>
     * <p/>
     * Finally, the implementer must ensure that <tt>x.compareTo(y)==0</tt> implies that <tt>sgn(x.compareTo(z)) ==
     * sgn(y.compareTo(z))</tt>, for all <tt>z</tt>.<p>
     * <p/>
     * It is strongly recommended, but <i>not</i> strictly required that <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.
     * Generally speaking, any class that implements the <tt>Comparable</tt> interface and violates this condition
     * should clearly indicate this fact.  The recommended language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * @param o the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than
     *         the specified object.
     * @throws ClassCastException if the specified object's type prevents it from being compared to this Object.
     */
    public int compareTo(Application o) {
        return name.compareTo(o.getName());
    }

    /**
     * Returns a string representation of the object. In general, the <code>toString</code> method returns a string that
     * "textually represents" this object. The result should be a concise but informative representation that is easy
     * for a person to read. It is recommended that all subclasses override this method.
     * <p/>
     * The <code>toString</code> method for class <code>Object</code> returns a string consisting of the name of the
     * class of which the object is an instance, the at-sign character `<code>@</code>', and the unsigned hexadecimal
     * representation of the hash code of the object. In other words, this method returns a string equal to the value
     * of: <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
