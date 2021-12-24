package entity;


/**
 * @author June
 * @date 2021/11/15 10:30
 */
public class EntityDic {                //数据字典
    private int id;
    private String firstname;
    private String secondname;
    private String thirdname;
    private String forthname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }

    public String getForthname() {
        return forthname;
    }

    public void setForthname(String forthname) {
        this.forthname = forthname;
    }
}
