package myapp.releaseActivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import net.simplifiedcoding.simplifiedcoding.R;
import net.simplifiedcoding.simplifiedcoding.SharedPrefManager;

import myapp.model.Product;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GeneralReleaseInfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GeneralReleaseInfFragment extends Fragment {

    TextView textViewQuantity, textViewProductname, textViewProductsymbol;
    private Button buttonScan2 /*, buttonAddProductActivity*/;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView list;

    public GeneralReleaseInfFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GeneralReleaseInfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GeneralReleaseInfFragment newInstance(String param1, String param2) {
        GeneralReleaseInfFragment fragment = new GeneralReleaseInfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_general_release_inf, container, false);


        //getting the current product
    /*    Product product = SharedPrefManager.getInstance(this).getProduct();

        //setting the values to the textviews
        textViewQuantity.setText(String.valueOf(product.getQuantity()));
        textViewProductname.setText(product.getName());
        textViewProductsymbol.setText(product.getSymbol());
    */
    }
}