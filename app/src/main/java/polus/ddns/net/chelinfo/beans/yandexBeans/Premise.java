
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Premise {

    @SerializedName("PremiseNumber")
    @Expose
    public String premiseNumber;
    @SerializedName("PostalCode")
    @Expose
    public PostalCode postalCode;

}
