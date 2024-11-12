package com.example.avance02_proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.avance02_proyecto.Adapter.Review;
import com.example.avance02_proyecto.Adapter.ReviewAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailTitle;
    ImageView detailImage;

    private List<Review> reviewList = new ArrayList<>();
    private ReviewAdapter reviewAdapter;

    private TextView averageRatingTextView;
    private RatingBar averageRatingBar;
    private TextView totalReviewsTextView;

    private ImageButton backButton;
    private ImageButton planoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailTitle = findViewById(R.id.detailTitle);
        detailImage = findViewById(R.id.detailImage);

        RecyclerView recyclerView = findViewById(R.id.recyclerView_comments);
        reviewAdapter = new ReviewAdapter(reviewList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(reviewAdapter);


        // Referencias a los elementos de la UI para promedio y total de opiniones
        averageRatingTextView = findViewById(R.id.averageRating);
        averageRatingBar = findViewById(R.id.ratingBar);
        totalReviewsTextView = findViewById(R.id.totalReviews);

        Button btnAddReview = findViewById(R.id.btn_add_review);
        btnAddReview.setOnClickListener(v -> openBottomSheetDialog());

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());

        planoButton = findViewById(R.id.planoButton);
        planoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, mostrarplano.class);
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getInt("Desc"));
            detailImage.setImageResource(bundle.getInt("Image"));
            detailTitle.setText(bundle.getString("Title"));
        }

        updateRatingSummary();
    }

    private void openBottomSheetDialog() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        View bottomSheetView = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);

        RatingBar ratingBarDialog = bottomSheetView.findViewById(R.id.ratingBar_dialog);
        EditText editTextComment = bottomSheetView.findViewById(R.id.editText_comment);
        Button btnSubmitReview = bottomSheetView.findViewById(R.id.btn_submit_review);

        btnSubmitReview.setOnClickListener(v -> {
            float rating = ratingBarDialog.getRating();
            String comment = editTextComment.getText().toString();

            if (!comment.isEmpty()) {
                reviewList.add(new Review(rating, comment));
                reviewAdapter.notifyDataSetChanged();
                updateRatingSummary();
                bottomSheetDialog.dismiss();
            } else {
                editTextComment.setError("Escribe un comentario");
            }
        });

        bottomSheetDialog.show();
    }

    private void updateRatingSummary() {
        if (reviewList.isEmpty()) {
            averageRatingTextView.setText("0.0");
            averageRatingBar.setRating(0);
            totalReviewsTextView.setText("0 opiniones");
            return;
        }

        // Calcular promedio de estrellas
        float totalRating = 0;
        for (Review review : reviewList) {
            totalRating += review.getRating();
        }
        float averageRating = totalRating / reviewList.size();

        // Actualizar UI
        averageRatingTextView.setText(String.format(Locale.getDefault(), "%.1f", averageRating));
        averageRatingBar.setRating(averageRating);
        totalReviewsTextView.setText(reviewList.size() + " opiniones");
    }
}