
package polus.ddns.net.chelinfo.beans.yandexBeans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Component {

    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("name")
    @Expose
    public String name;

}
