package com.example.admin.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.Toast;
import android.widget.Button;
import android.graphics.Paint;
import android.view.inputmethod.EditorInfo;
import static android.support.v7.appcompat.R.styleable.Toolbar;
import static com.example.admin.todolist.R.styleable.Toolbar_navigationIcon;

/**
 * Created by Admin on 11/5/2016.
 */

public class PagerFragment extends Fragment {
    static TextView title_fragment;
    static EditText desc_fragment;
    DBHelper myDb;
    ListItem item;
    View view;
    public PagerFragment(){}
    public PagerFragment(ListItem listItem){
        item=listItem;
    }
    @Override
    public View onCreateView(final LayoutInflater inflater,final ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.pager_fragment_layout,container,false);
        title_fragment=(TextView)view.findViewById(R.id.title_fragment);
        desc_fragment= (EditText) view.findViewById(R.id.desc_fragment);
        title_fragment.setText(item.getItem_name());
        desc_fragment.setText(item.getDesc());
        return view;
    }
}
