package FPT.PRO1122.Nhom3.DuAn1.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import FPT.PRO1122.Nhom3.DuAn1.R;
import FPT.PRO1122.Nhom3.DuAn1.adapter.AdapterBanner;
import FPT.PRO1122.Nhom3.DuAn1.model.MonAn;

public class Home extends Fragment {
    private ViewPager2 viewPage2;
    private List<Integer> arrayImg;
    AdapterBanner adapterBanner;
    int index;
    RecyclerView recyclerViewFood;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Khởi tạo các view
        viewPage2 = view.findViewById(R.id.viewPage2);
        recyclerViewFood = view.findViewById(R.id.recyclerFood);
        setSlider();
        MonAn monAn = new MonAn("1","Banh Mi Sai Gon Thom Ngon","Bánh mì Sài Gòn chỉ đơn giản là bánh mì nóng giòn vẫn thường được dùng để kẹp thịt nguội, chả lụa, pate hoặc dùng kèm với các món mặn như bò kho, ragu..."
        ,"20000","https://firebasestorage.googleapis.com/v0/b/duan1-2e5d9.appspot.com/o/b%C3%A1nh%20m%C3%AC%20s%C3%A0i%20g%C3%B2n.png?alt=media&token=382cb0af-d40b-400a-989b-37401e5490aa");
        FirebaseDatabase.getInstance().getReference("foods").child("food").child("1").setValue(monAn);

    }

    private void setSlider() {
        arrayImg = new ArrayList<>();
        arrayImg.add(R.drawable.banner1);
        arrayImg.add(R.drawable.banner2);
        arrayImg.add(R.drawable.banner3);
        arrayImg.add(R.drawable.banner4);
        arrayImg.add(R.drawable.banner5);
        adapterBanner = new AdapterBanner(getContext(),arrayImg);

        viewPage2.setAdapter(adapterBanner);

        viewPage2.setClipToPadding(false);
        viewPage2.setClipChildren(false);
        viewPage2.setOffscreenPageLimit(5);
        viewPage2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        // tạo hiệu ứng khi vuốt
        viewPage2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                Math.abs(position);
                page.setScaleY(0.85f + Math.abs(position)*0.15f); // thu nhỏ theo trục y tạo hiệu ứng trượt

            }
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                next();
                handler.postDelayed(this,3000);
            }
        };
        handler.post(runnable);
    }
    public void next () {
        if (index < 2) {
            index ++;
            viewPage2.setCurrentItem(index);
//            Log.d("giahyng",index+"");
        } else {
            index = -1;
        }
    }
}

