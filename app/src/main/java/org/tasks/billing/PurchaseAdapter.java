package org.tasks.billing;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import org.tasks.Callback;
import org.tasks.R;
import org.tasks.locale.Locale;

public class PurchaseAdapter extends ListAdapter<Integer, PurchaseHolder> {

  private final Activity activity;
  private final Locale locale;
  private final Callback<Integer> onPriceChanged;
  private int selected;

  PurchaseAdapter(Activity activity, Locale locale, Callback<Integer> onPriceChanged) {
    super(new DiffCallback());
    this.activity = activity;
    this.locale = locale;
    this.onPriceChanged = onPriceChanged;
  }

  public int getSelected() {
    return selected;
  }

  public void setSelected(int price) {
    int previous = selected;
    this.selected = price;
    notifyItemChanged(previous - 1, null);
    notifyItemChanged(price - 1, null);
  }

  @NonNull
  @Override
  public PurchaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = activity.getLayoutInflater().inflate(R.layout.dialog_purchase_cell, parent, false);
    return new PurchaseHolder(view, onPriceChanged, locale);
  }

  @Override
  public void onBindViewHolder(@NonNull PurchaseHolder holder, int position) {
    int price = position + 1;
    holder.bind(price, price == selected);
  }

  private static class DiffCallback extends ItemCallback<Integer> {
    @Override
    public boolean areItemsTheSame(@NonNull Integer oldItem, @NonNull Integer newItem) {
      return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Integer oldItem, @NonNull Integer newItem) {
      return true;
    }
  }
}
