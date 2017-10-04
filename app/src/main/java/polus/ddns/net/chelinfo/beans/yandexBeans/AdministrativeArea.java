
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdministrativeArea {

    @SerializedName("AdministrativeAreaName")
    @Expose
    public String administrativeAreaName;
    @SerializedName("SubAdministrativeArea")
    @Expose
    public SubAdministrativeArea subAdministrativeArea;

}
