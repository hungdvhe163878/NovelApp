package com.example.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private DBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbContext = new DBContext(this);
        SQLiteDatabase db = dbContext.getWritableDatabase();
        insertCategory(db, "Ngôn Tình");
        insertCategory(db, "Truyện Cươì");
        insertCategory(db, "Truyện Ma");
        insertCategory(db, "Trinh Thám");
        insertUser(db, "Tran Quang Dieu", "TranQuangDieu@example.com", "password123456", 2);
        insertUser(db, "Do Tung Lam", "DoTungLam@example.com", "password123456", 2);

        insertFavoriteComic(db, 2, 1);

//        insertRole(db, "Admin");
//        insertRole(db, "User");
//
//        insertUser(db, "John Doe", "john@example.com", "password123", 1);
//        byte[] comicImage = drawableToByteArray(R.drawable.img1);
//
//        insertComic(db, "Comic Title", "Comic Description", "Comic Author", "Comic Detail", "2023-07-12", comicImage, "1");
//        insertFavoriteComic(db, 1, 1);
//        insertCategory(db, "Adventure");

//        byte[] comicImage = drawableToByteArray(R.drawable.img2);
//        insertComic(db, "TRỌNG SINH TU TIÊN TẠI ĐÔ THỊ", "Trọng Sinh Tu Tiên Tại Đô Thị - Hoàn thành - Ngã Ý Như Đao\n" +
//                "\n" +
//                "Giới thiệu truyện xuyên không đặc sắc này:\n" +
//                "\n" +
//                "\"Tiểu Trần! Cháu đã tỉnh rồi!\"\n" +
//                "\n" +
//                "Người phụ nữ xinh đẹp này thấy Diệp Trần ngồi dậy, khuôn mặt tràn đầy vui mừng, nhanh chóng đi tới.\n" +
//                "\n" +
//                "\"Dì Lam...\"\n" +
//                "\n" +
//                "Cho dù Diệp Trần tung hoành ngang dọc ở Tu Chân giới mấy trăm năm, từ trước đến nay luôn được biết đến là người lãnh khốc vô tình, nhưng giờ phút này nước mắt không kìm được mà rơi đầy trên mặt.\n" +
//                "\n" +
//                "Tên của người phụ nữ xinh đẹp này là Tô Lam, xem như là mẹ nuôi của Diệp Trần.\n" +
//                "\n" +
//                "Năm đó, ở lúc Diệp Trần mới chín tuổi, mẹ của hắn đã qua đời, mà Tô Lam lại là chị em tốt nhất của mẹ hắn, cho nên trước khi mẹ Diệp Trần chết, đã đem Diệp Trần giao phó cho Tô Lam nuôi dưỡng.\n" +
//                "\n" +
//                "Tô Lam không phụ lòng của chị em tốt giao phó, một mực coi Diệp Trần như chính con mình đẻ ra mà nuôi dưỡng, thậm chí trên mức độ yêu thương, còn hơn hẳn đứa con gái ruột thịt của mình.\n" +
//                "\n" +
//                "Thật không may, người tốt không được báo đáp tốt, một đời Tô Lam đều tương đối khổ cực, trước thời gian đó mấy năm gặp người không quen, gả cho một người đàn ông cặn bã, sau khi ly hôn Tô Lam tự mình mở một quán cơm, vẫn còn bị chồng cũ thường xuyên tới gây rối, về sau lại cuốn vào bên trong tranh chấp thân thế của Diệp Trần, cuối cùng lại bị anh trai cùng cha khác mẹ của Diệp Trần là Diệp Vô Thương tàn nhẫn giết hại...\n" +
//                "\n" +
//                "Tô Lam chết, là tiếc nuối lớn nhất của Diệp Trần ở kiếp trước, cũng là nguyên nhân chủ yếu về sau làm cho tính tình của hắn thay đổi rất lớn!\n" +
//                "\n" +
//                "Tô Lam đương nhiên không biết, Diệp Trần trước mắt, đã sớm không còn là Diệp Trần trước đó, mà là Diệp Trần mang theo trí nhớ tám trăm năm, Cuồng Đế Diệp Trần từ Tu Chân giới trọng sinh trở về!", "Comic Author", "Ngày xửa ngày xưa, có một con cú già sống trên một cây sồi to. Mỗi ngày, nó đều phóng tầm mắt ra thật xa để quan sát những điều xảy ra xung quanh mình. Có khi nó nhìn thấy một cậu bé đang giúp ông lão xách một cái túi to, có khi nó nhìn thấy một cô con gái đang cằn nhằn mẹ mình. Ngày qua ngày, con cú nhìn thấy được rất nhiều thứ nhưng nó vẫn giữ im lặng về những điều mà mình thấy.\n" +
//                "\n" +
//                "Từ từ, con cú già bắt đầu nói ít hơn và thính giác của nó dần trở nên tốt hơn. Bây giờ nó có thể nghe rõ những cuộc nói chuyện của mọi người. Một ngày, con cú già nghe thấy một người phụ nữ nói với ai đó rằng có một con voi nhảy qua hàng rào. Một ngày khác, con cú lại nghe thấy một người đàn ông nói với ai đó rằng mình là con người hoàn hảo và chưa bao giờ mắc phải sai lầm gì.\n" +
//                "\n" +
//                "Mỗi ngày trôi qua, con cú già lại nói ít hơn và nghe nhiều hơn. Nhờ vậy, nó biết được tất cả mọi thứ xảy ra xung quanh, dù không có ở đó. Dần dần, con cú già trở nên khôn ngoan hơn và nổi tiếng vì sự khôn ngoan ấy.", "19/11/2022", comicImage, "1");
        displayComicImage(db);
    }
    private void displayComicImage(SQLiteDatabase db) {
        Cursor cursor = db.query("comic", new String[]{"img"}, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            byte[] img = cursor.getBlob(cursor.getColumnIndexOrThrow("img"));
            ImageView comicImageView = findViewById(R.id.comicImageView);
            comicImageView.setImageBitmap(byteArrayToBitmap(img));
        }
        cursor.close();
    }
    private Bitmap byteArrayToBitmap(byte[] byteArray) {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }
    private byte[] drawableToByteArray(int drawableId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableId);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    private void insertRole(SQLiteDatabase db, String roleName) {
        ContentValues values = new ContentValues();
        values.put("name", roleName);
        db.insert("role", null, values);
    }
    private void insertUser(SQLiteDatabase db, String name, String email, String password, int role) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("email", email);
        values.put("password", password);
        values.put("role", role);
        db.insert("user", null, values);
    }
    private void insertComic(SQLiteDatabase db, String title, String description, String author, String detail, String dateplublic, byte[] img, String categories) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("description", description);
        values.put("author", author);
        values.put("detail", detail);
        values.put("dateplublic", dateplublic);
        values.put("img", img);
        values.put("categories", categories);
        db.insert("comic", null, values);
    }

    private void insertFavoriteComic(SQLiteDatabase db, int idUser, int idComic) {
        ContentValues values = new ContentValues();
        values.put("iduser", idUser);
        values.put("idComic", idComic);
        db.insert("favoriteComic", null, values);
    }
    private void insertCategory(SQLiteDatabase db, String categoryName) {
        ContentValues values = new ContentValues();
        values.put("name", categoryName);
        db.insert("categories", null, values);
    }

}