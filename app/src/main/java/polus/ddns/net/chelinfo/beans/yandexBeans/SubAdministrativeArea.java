
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubAdministrativeArea {

    @SerializedName("SubAdministrativeAreaName")
    @Expose
    public String subAdministrativeAreaName;
    @SerializedName("Locality")
    @Expose
    public Locality locality;

}
