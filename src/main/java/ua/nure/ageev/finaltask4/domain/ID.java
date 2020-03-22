package ua.nure.ageev.finaltask4.domain;

/**
 * Abstract class for ID ;
 *
 * @author A.Ageev
 *
 */
abstract class ID<ID>{
    protected ID id;
    public ID() {
        //not need to implement
    }
    public ID(ID id) {
        this.id = id;
    }
    public ID getId() {
        return id;
    }
    public void setId(ID id) {
        this.id = id;
    }
}
