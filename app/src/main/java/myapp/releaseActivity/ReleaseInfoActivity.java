package myapp.releaseActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import net.simplifiedcoding.simplifiedcoding.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtilsHC4;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import myapp.JSONParser;
import myapp.api.ApiBuilder;
import myapp.api.MyApi;
import myapp.model.Employee;
import myapp.model.Product;
import myapp.model.ProductRelease;
import myapp.model.ProductStatus;
import myapp.model.Release;
import myapp.model.ReleaseStatus;
import myapp.modelView.ProductsOrderView;
import myapp.modelView.ReleaseView;

import static myapp.api.URLs.URL_GET_RELEASE;


public class ReleaseInfoActivity extends AppCompatActivity {

    protected RecyclerView mRecyclerView;
    //    protected ProductAdapter mProductAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<ProductsOrderView> mProductViews;

    protected MyApi apiService;

    protected List<Product> mProductsList;
    protected List<ReleaseView> mReleaseViews;

    protected List<Release> mReleasesList;
    protected List<ProductRelease> mProductReleaseList;
    private final String GET_RELEASE_TAG = "GET_RELEASE";

    JSONParser jsonParser = new JSONParser();

    public ArrayList<HashMap<String, String>> release;

    public ArrayList<HashMap<String, String>> products;

    // JSON Node names
    private static final String TAG_SUCCESS = "error";
    private static final String TAG_MESSAGE = "message";

    private static final String TAG_ID = "id";

    private static final String TAG_EMPLOYEES = "employeeslist";

    private static final String TAG_EMPLOYEE = "employee";

    //private static final String TAG_NAME = "name";

    // employees JSONArray
    JSONArray employees = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.release_info_activity);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout, new GeneralReleaseInfFragment()).commit();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gen_inf_lay:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout,
                                new GeneralReleaseInfFragment()).commit();
                break;
            case R.id.details_lay:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout,
                                new DetailsReleaseFragment()).commit();
                break;
        }
          /*  case R.id.profile_lay:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout,
                                new ProfileFragment()).commit();
                break;
        }


     /*   TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2")); */

            //     mRecyclerView = findViewById(R.id.releasesProdRecyclerView);
            //     mLayoutManager = new LinearLayoutManager(this);

        apiService = new ApiBuilder().getApiService();
        mReleasesList = new ArrayList<Release>();
        mReleaseViews = new ArrayList<ReleaseView>();
        mProductReleaseList = new ArrayList<ProductRelease>();
        mProductViews = new ArrayList<ProductsOrderView>();

            // get releases via async request
            // getReleases();

//        mProductAdapter = new ProductAdapter(mProductViews);
            // Set CustomAdapter as the adapter for RecyclerView.
//        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setAdapter(mProductAdapter);

        release = new ArrayList<HashMap<String, String>>();
        products = new ArrayList<HashMap<String, String>>();
//        new getRelease().execute();

 /*       RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
    }
*/
    class getRelease extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ReleaseInfoActivity.this.setProgressBarIndeterminateVisibility(true);
        }

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub

            // Building Parameters
            List<NameValuePair> parametres = new ArrayList<NameValuePair>();

            HttpClient client = HttpClientBuilder.create().build();
            HttpGet request = new HttpGet(URL_GET_RELEASE);
            String json = null;
            try {
                HttpResponse response = client.execute(request);
                HttpEntity httpEntity = response.getEntity();
                json = EntityUtilsHC4.toString(httpEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

            /**
             * After completing background task Dismiss the progress dialog
             * **/
        protected void onPostExecute(String json) {

            Boolean result = null;
            String message = null;

            Log.d("This release: ", json);
            // dismiss the dialog after release
            try {
                JSONObject jsonObj = new JSONObject(json);
                // wybranie tablicy release
                result = jsonObj.getBoolean("error");
                message = jsonObj.getString("message");

                JSONArray jsonArray = jsonObj.getJSONArray("object");
                //for (int i = 0; i < jsonArray.length(); i++) {
                    // Wybranie pojedyńczego obiektu w tablicy - release
                    JSONObject release = jsonArray.getJSONObject(0);
                    // Wybranie poszczególnych
                    // id= jsonObj.getString("id").toInteger ?? // id konwertuj na int
                    //String id = jsonObj2.getString("id");
                    //String productName = jsonObj2.getString("productName");
                    //String quantity = jsonObj2.getString("quantity");

                    String id = release.getString("id");
                    String date_creation = release.getString("creationDate");
                    String status = release.getString("status");
                    JSONObject empl = release.getJSONObject("employee");
                    Employee employee = new Employee(empl.getInt("id"),
                            empl.getString("symbol"),empl.getString("name"),
                            empl.getString("surname"));
                JSONArray jsonArray2 = jsonObj.getJSONArray("productsRelease");

                for (int j = 0; j < jsonArray2.length(); j++) {
                        // Wybranie pojedyńczego obiektu w tablicy - release
                    JSONObject productsRelease = jsonArray2.getJSONObject(j);

                    String statusProd = productsRelease.getString("status");
                    String reqQuantity = productsRelease.getString("quantity");
            //        String status = release.getString("status");
                    JSONObject prod = productsRelease.getJSONObject("product");

                //    JSONObject prodRel = jsonObj2.getJSONObject("productsRelease");

                //    JSONObject prod = jsonObj2.getJSONObject("product");
                    Product product = new Product(prod.getInt("id"),
                            prod.getInt("quantity"),prod.getString("name"),
                            prod.getString("symbol"));

                //    JSONObject prodRel = jsonObj2.getJSONObject("productsRelease");

     //               List<myapp.model.ProductRelease> productsRelease = jsonArray2;/* = new ProductRelease(prodRel.getProduct(),
     //                       prodRel.getStatus(), prodRel.getReqQuantity());*/
                 // Product product = prodRel.getProduct();

                    HashMap<String, String> map = new HashMap<String, String>();

                    ProductRelease prodRel = new ProductRelease(product, ProductStatus.valueOf(statusProd), Integer.parseInt(reqQuantity));
                    mProductReleaseList.add(prodRel);

                    Release rel = new Release(Integer.parseInt(id), employee,  ReleaseStatus.enumOfValue(status), //ReleaseStatus.valueOf(status),
                            mProductReleaseList, date_creation, null);
                    mReleasesList.add(rel);
                    mProductReleaseList.add(prodRel);
                    mReleaseViews.add(new ReleaseView(rel.getId(),
                            rel.getCreationDateTime(),
                            rel.getStatus().name(),
                            rel.getEmployee().getSurname(),
                            rel.getEmployee().getName()/*,
                            rel.getProductsRelease().get(j).getProduct().getName(),
                            rel.getProductsRelease().get(j).getReqQuantity()*/));

                    // adding each child node to HashMap key => value
                    map.put("id", id);
                    map.put("date_creation", date_creation);
                    map.put("status", status);
                    map.put("id_employee", String.valueOf(employee.getId()));
                 //   map.put("id_release", id);



                    // adding HashList to ArrayList
                    //releases.add(map);
                }
        //        mReleaseAdapter.notifyDataSetChanged();
            } catch(Exception e) {
                Log.e("Error", e.getMessage());
                Toast.makeText(getBaseContext(), "Error while parsing response - " + e.getMessage(),  Toast.LENGTH_LONG).show();
            }

            if (result != null && (result == false)) {

                ReleaseInfoActivity.this
                        .setProgressBarIndeterminateVisibility(false);
                /*
                // updating UI from Background Thread
                runOnUiThread(new Runnable() {
                    public void run() {

                        ListAdapter adapter = new SimpleAdapter(
                                ReleaseActivity.this, releases,
                                R.layout.content_release, new String[] { //TAG_ID,
                                "id", "date_creation", "status", "surname", "name"}, new int[] { //R.id.id,
                                R.id.id_release, R.id.date_creation,  R.id.status, R.id.employee_surname, R.id.employee_name});
                        // updating listview
                        setListAdapter(adapter);
                    }
                });*/


                Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG)
                        .show();

            }
        }

    }

/*    public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        private static final String TAG = "ProductAdapter";

        private List<ProductsOrderView> mProductsView;

        // VievHolder do przechowywania widoków elementów listy
        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mIdTextView;
            private final TextView mNameTextView;
            private final TextView mStatusTextView;
            private final TextView mRequestedQuantityTextView;
         //   private final TextView mEmployeeNameTextView;

            public ViewHolder(View v) {
                super(v);
                // Define click listener for the ViewHolder's View.
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                      //  openReleaseInfoActivity();
                    }
                });
                mIdTextView = (TextView) v.findViewById(R.id.id_product_order);
                mNameTextView = (TextView) v.findViewById(R.id.product_name);
                mRequestedQuantityTextView = (TextView) v.findViewById(R.id.requested_quantity);
                mStatusTextView = (TextView) v.findViewById(R.id.product_order_status);
            //    mEmployeeNameTextView = (TextView) v.findViewById(R.id.employee_name);
            }



            public TextView getIdTextView() {
                return mIdTextView;
            }

            public TextView getNameTextView() {
                return mNameTextView;
            }

            public TextView getRequestedQuantityTextView() {
                return mRequestedQuantityTextView;
            }

            public TextView getStatusTextView() {
                return mStatusTextView;
            }

        /*    public TextView getEmployeeNameTextView() {
                return mEmployeeNameTextView;
            }*/
        }
    }
//}



        /**
         * Initialize the dataset of the Adapter.
         *
         * @param prodView List<ReleaseView> containing the data to populate views to be used by RecyclerView.
         */
/*        public ProductAdapter(List<ProductsOrderView> prodView) {
            mProductsView = prodView;
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_product_orders, parent, false);
            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Log.d(TAG, "Element " + position + " set.");
            // Get element from your dataset at this position and replace the contents of the view
            // with that element
            holder.getIdTextView().setText(String.valueOf(mProductsView.get(position).getId()));
            holder.getNameTextView().setText(mProductsView.get(position).getName());
            holder.getRequestedQuantityTextView().setText(String.valueOf(mProductsView.get(position).getReqQuantity()));
            holder.getStatusTextView().setText(mProductsView.get(position).getStatus());
         //   holder.getEmployeeNameTextView().setText(mReleaseView.get(position).getName());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mProductsView == null ? 0 : mProductsView.size();
        }

        public List<ProductsOrderView> getProductView() {
            return mProductsView;
        }
    }

}

 */
