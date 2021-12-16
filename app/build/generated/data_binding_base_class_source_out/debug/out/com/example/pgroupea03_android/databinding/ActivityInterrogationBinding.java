// Generated by view binder compiler. Do not edit!
package com.example.pgroupea03_android.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.pgroupea03_android.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityInterrogationBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final FragmentContainerView fragmentActivityTeacherContainer;

  private ActivityInterrogationBinding(@NonNull ConstraintLayout rootView,
      @NonNull FragmentContainerView fragmentActivityTeacherContainer) {
    this.rootView = rootView;
    this.fragmentActivityTeacherContainer = fragmentActivityTeacherContainer;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityInterrogationBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityInterrogationBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_interrogation, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityInterrogationBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.fragment_activityTeacher_container;
      FragmentContainerView fragmentActivityTeacherContainer = ViewBindings.findChildViewById(rootView, id);
      if (fragmentActivityTeacherContainer == null) {
        break missingId;
      }

      return new ActivityInterrogationBinding((ConstraintLayout) rootView,
          fragmentActivityTeacherContainer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}