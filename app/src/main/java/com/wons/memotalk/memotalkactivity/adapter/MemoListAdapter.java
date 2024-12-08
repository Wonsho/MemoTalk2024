package com.wons.memotalk.memotalkactivity.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wons.memotalk.R;
import com.wons.memotalk.databinding.MemoFileBinding;
import com.wons.memotalk.databinding.MemoImgBinding;
import com.wons.memotalk.databinding.MemoTextBinding;
import com.wons.memotalk.databinding.MemoTodoBinding;
import com.wons.memotalk.databinding.MemoUrlBinding;
import com.wons.memotalk.entity.memo_data.MemoData;
import com.wons.memotalk.entity.memo_data.MemoDataType;
import com.wons.memotalk.entity.memo_data.MemoText;
import com.wons.memotalk.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class MemoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MemoData> itemList;

    public static class MemoTextViewHolder extends RecyclerView.ViewHolder {
        private final MemoTextBinding binding;

        public MemoTextViewHolder(View view) {
            super(view);
            binding = MemoTextBinding.bind(view);
        }

        public MemoTextBinding getBinding() {
            return binding;
        }
    }


    //todo 보류
    public static class MemoImageViewHolder extends RecyclerView.ViewHolder {
        private final MemoImgBinding binding;

        public MemoImageViewHolder(View view) {
            super(view);
            binding = MemoImgBinding.bind(view);
        }

        public MemoImgBinding getBinding() {
            return this.binding;
        }
    }


    //todo 보류
    public static class MemoFileViewHolder extends RecyclerView.ViewHolder {
        private final MemoFileBinding binding;

        public MemoFileViewHolder(View view) {
            super(view);
            binding = MemoFileBinding.bind(view);
        }

        public MemoFileBinding getBinding() {
            return this.binding;
        }
    }


    public static class MemoTodoViewHolder extends RecyclerView.ViewHolder {
        private final MemoTodoBinding binding;

        public MemoTodoViewHolder(View view) {
            super(view);
            binding = MemoTodoBinding.bind(view);
        }

        public MemoTodoBinding getBinding() {
            return this.binding;
        }
    }


    //todo 보류
    public static class MemoUrlViewHolder extends RecyclerView.ViewHolder {
        private final MemoUrlBinding binding;

        public MemoUrlViewHolder(View view) {
            super(view);
            binding = MemoUrlBinding.bind(view);
        }

        public MemoUrlBinding getBinding() {
            return binding;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = null;
        switch (MemoDataType.fromTypeCode(viewType)) {
            case TEXT: {
                //todo 1
                view = MemoTextBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false).getRoot();
                return new MemoTextViewHolder(view);
            }
            case URL: {
                //todo 3
                view = MemoUrlBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false).getRoot();
                break;
            }
            case FILE: {
                //todo 5
                view = MemoFileBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false).getRoot();
                break;
            }
            case TODO: {
                //todo 2
                view = MemoTodoBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false).getRoot();
                break;
            }
            case IMAGE: {
                //todo 4
                view = MemoImgBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false).getRoot();
                break;
            }
        }
        return null;
    }

    // Replace the contents of a view (invoked by the layout manager)

    //아이템 카테고리에 따라 뷰 바인딩
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {
        switch (MemoDataType.fromTypeCode(viewHolder.getItemViewType())) {
            case TEXT: {
                MemoTextBinding binding = ((MemoTextViewHolder) viewHolder).getBinding();
                MemoData data = itemList.get(position);
                MemoText memoText = (MemoText) data.getItemData();
                binding.tvMemo.setText(memoText.value);
                binding.tvTime.setText(data.getDate(binding.getRoot().getContext().getString(R.string.memo_time_format)));
                if (data.getCheck()) {
                    binding.markCheck.setVisibility(View.VISIBLE);
                } else {
                    binding.markCheck.setVisibility(View.GONE);
                }

                if (data.getExclamation()) {
                    binding.markNotion.setVisibility(View.VISIBLE);
                } else {
                    binding.markNotion.setVisibility(View.GONE);
                }

                break;
            }
            case FILE: {

                break;
            }
            case IMAGE: {

                break;
            }
            case TODO: {

                break;
            }
            case URL: {

                break;
            }

            default: {
                //todo error
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int type = this.itemList.get(position).getCategory();
        return type;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<MemoData> list) {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }
        this.itemList = list;
    }
}
