package upload.cgs.cn.sczp;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by CGS on 2016/12/18.
 */
public class ProcessBean extends BmobObject {
    private String name;
    private String password;
    private BmobFile file;
    private BmobFile file1;
    private BmobFile file2;
    private BmobFile file3;
    private BmobFile file4;
    private BmobFile file5;
    private int fileCount;

    public BmobFile getFile3() {
        return file3;
    }

    public void setFile3(BmobFile file3) {
        this.file3 = file3;
    }

    public BmobFile getFile4() {
        return file4;
    }

    public void setFile4(BmobFile file4) {
        this.file4 = file4;
    }

    public BmobFile getFile5() {
        return file5;
    }

    public void setFile5(BmobFile file5) {
        this.file5 = file5;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public ProcessBean(){
    }
    public ProcessBean(BmobFile file){
        this.file=file;
    }
    public ProcessBean(BmobFile file,BmobFile file2,BmobFile file1){
        this.file=file;
        this.file1=file1;
        this.file2=file2;
    }
    public ProcessBean(BmobFile file,BmobFile file1){
        this.file=file;
        this.file1=file1;

    }
    public ProcessBean(BmobFile file,BmobFile file2,BmobFile file1,BmobFile file3){
        this.file=file;
        this.file1=file1;
        this.file2=file2;
        this.file3=file3;
    }
    public ProcessBean(BmobFile file,BmobFile file2,BmobFile file1,BmobFile file3,BmobFile file4){
        this.file=file;
        this.file1=file1;
        this.file2=file2;
        this.file3=file3;
        this.file4=file4;
    }
    public ProcessBean(BmobFile file,BmobFile file2,BmobFile file1,BmobFile file3,BmobFile file4,BmobFile file5){
        this.file=file;
        this.file1=file1;
        this.file2=file2;
        this.file3=file3;
        this.file4=file4;
        this.file5=file5;
    }

    public BmobFile getFile1() {
        return file1;
    }

    public void setFile1(BmobFile file1) {
        this.file1 = file1;
    }

    public BmobFile getFile2() {
        return file2;
    }

    public void setFile2(BmobFile file2) {
        this.file2 = file2;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BmobFile getFile() {
        return file;
    }

    public void setFile(BmobFile file) {
        this.file = file;
    }
}
