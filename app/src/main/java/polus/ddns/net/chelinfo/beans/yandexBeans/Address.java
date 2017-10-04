
package polus.ddns.net.chelinfo.beans.yandexBeans;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {

    @SerializedName("country_code")
    @Expose
    public String countryCode;
    @SerializedName("postal_code")
    @Expose
    public String postalCode;
    @SerializedName("formatted")
    @Expose
    public String formatted;
    @SerializedName("Components")
    @Expose
    public List<Component> components = null;

}
