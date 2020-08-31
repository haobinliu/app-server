package server.entity;


import java.util.Arrays;

/**
 * @author liubinhao
 * @date 2020/8/9
 */
public class OutputFile {

    private String fileType;

    private String fileName;

    private String filePath;

    private byte[] fileContent;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String toString() {
        return "OutputFile{" +
                "fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileContent=" + Arrays.toString(fileContent) +
                '}';
    }
}
