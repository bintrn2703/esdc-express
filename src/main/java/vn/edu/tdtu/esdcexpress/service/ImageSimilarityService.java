package vn.edu.tdtu.esdcexpress.service;

import java.util.List;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSimilarityService {
    public Double ImageSimilarityCheck (String imagePath1, String imagePath2) {
        // Load OpenCV library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Đường dẫn tới hai hình ảnh cần so sánh
        /*String imagePath1 = "C:\\Users\\phatp\\IdeaProjects\\Test\\pexels-photo-8947493.jpeg";
        String imagePath2 = "C:\\Users\\phatp\\IdeaProjects\\Test\\pexels-photo-8947493-1.jpeg";*/

        // Đọc hai hình ảnh
        Mat image1 = Imgcodecs.imread(imagePath1);
        Mat image2 = Imgcodecs.imread(imagePath2);

        // Chuyển đổi hình ảnh thành ảnh grayscale
        Mat grayImage1 = new Mat();
        Mat grayImage2 = new Mat();
        Imgproc.cvtColor(image1, grayImage1, Imgproc.COLOR_BGR2GRAY);
        Imgproc.cvtColor(image2, grayImage2, Imgproc.COLOR_BGR2GRAY);

        // Điều chỉnh kích thước của hai hình ảnh để đảm bảo cùng kích thước
        Size size = new Size(300, 300); // Kích thước mới cho hình ảnh
        Imgproc.resize(grayImage1, grayImage1, size);
        Imgproc.resize(grayImage2, grayImage2, size);

        // Tính histogram của hai hình ảnh
        Mat histImage1 = new Mat();
        Mat histImage2 = new Mat();
        Imgproc.calcHist(List.of(new Mat[]{grayImage1}), new MatOfInt(0), new Mat(), histImage1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(List.of(new Mat[]{grayImage2}), new MatOfInt(0), new Mat(), histImage2, new MatOfInt(256), new MatOfFloat(0, 256));

        // Chuẩn hóa histogram
        Core.normalize(histImage1, histImage1, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        Core.normalize(histImage2, histImage2, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        // Tính hệ số tương đồng giữa hai histogram
        double similarity = Imgproc.compareHist(histImage1, histImage2, Imgproc.HISTCMP_CORREL);

        // In ra kết quả
       return similarity;
    }
}
