package ua.nure.ageev.finaltask4.domain;

/**
 * Abstract class for ID ;
 *
 * @author A.Ageev
 */
public class LongID extends ID<Long> {
    private static final long serialVersionUID = -6889036256149495388L;

    public LongID() {
        super();
    }

    public LongID(Long o) {
        super(o);
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }
}
