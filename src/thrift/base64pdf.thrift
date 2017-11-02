namespace java com.lihao.netty.base64pdf

namespace php base64pdf


typedef i32 int
typedef string String

struct PdfRquest{
    1:required  String filename,
    2:required String pdfcontent,
    3:required String filepath,
}

struct Callback{
    1:int code,
    2:String message,

}

service Base64PdfService{
 Callback createPdf(1:required PdfRquest pdfRquest);

}