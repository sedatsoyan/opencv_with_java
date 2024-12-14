import org.opencv.core.Mat;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ımage {
    public BufferedImage toBUfferImage(Mat matrix){
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (matrix.channels() >1){
            type = BufferedImage.TYPE_3BYTE_BGR;

        }
        int buffersize = matrix.channels() * matrix.cols() * matrix.rows();
        byte[] buffer = new byte[buffersize];

        matrix.get(0,0,buffer);
        BufferedImage ımage = new BufferedImage(matrix.cols(),matrix.rows(),type);
        final  byte[] targetpixels = ((DataBufferByte) ımage.getRaster().getDataBuffer()).getData();
        System.arraycopy(buffer,0,targetpixels,0,buffer.length);
        return ımage;
    }

}
