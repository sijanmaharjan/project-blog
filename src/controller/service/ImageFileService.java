package controller.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.List;

public interface ImageFileService {
    void uploadFile(String blogId, HttpServletRequest request, Part filePart) throws IOException;
    void removeFile(HttpServletRequest request, String blogId);
    void removeFiles(HttpServletRequest request, List<String> images);
}
