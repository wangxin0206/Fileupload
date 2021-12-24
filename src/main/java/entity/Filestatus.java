package entity;

/**
 * @author June
 * @date 2021/11/10 21:15
 */
public class Filestatus {               //文件状态信息实体类
    private int id;
    private String filename;
    private String filetype;
    private String uploader;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private boolean filestatus;
    private String filepath;

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getFilename() {
        return filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public boolean getFilestatus()
    {
        return filestatus;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setFilestatus(boolean filestatus) {
        this.filestatus = filestatus;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public Filestatus() {
        super();
    }

    public Filestatus(String filename, String filetype, String uploader, boolean filestatus,String filepath) {
        this.filename = filename;
        this.filetype = filetype;
        this.uploader = uploader;
        this.filestatus = filestatus;
        this.filepath = filepath;
    }
}
