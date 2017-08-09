package polus.ddns.net.chelinfo.beans;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by golit on 02.08.2017.
 */

public class NewsListItem implements Parcelable {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("restLink")
    @Expose
    private String restLink;
    @SerializedName("showNewsList")
    @Expose
    private boolean showNewsList;//Определяет видимость раздела 0/1
    @SerializedName("typeFinder")
    @Expose
    private boolean typeFinder; //Определяет есть ли поиск в этом разделе 0/1

    public NewsListItem(Parcel in) {
        id =in.readLong();
        name = in.readString();
        restLink = in.readString();
        showNewsList = (Boolean) in.readValue(null);
        typeFinder = (Boolean) in.readValue(null);
    }

    public NewsListItem(long id, String name, String restLink, boolean showNewsList, boolean typeFinder) {
        this.id = id;
        this.name = name;
        this.restLink = restLink;
        this.showNewsList = showNewsList;
        this.typeFinder = typeFinder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestLink() {
        return restLink;
    }

    public void setRestLink(String restLink) {
        this.restLink = restLink;
    }

    public boolean isShowNewsList() {
        return showNewsList;
    }

    public void setShowNewsList(boolean showNewsList) {
        this.showNewsList = showNewsList;
    }

    public boolean isTypeFinder() {
        return typeFinder;
    }

    public void setTypeFinder(boolean typeFinder) {
        this.typeFinder = typeFinder;
    }

    @Override
    public String toString() {
        return "NewsListItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restLink='" + restLink + '\'' +
                ", showNewsList=" + showNewsList +
                ", typeFinder=" + typeFinder +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(restLink);
        dest.writeValue(showNewsList);
        dest.writeValue(typeFinder);
    }
    public static final Creator<NewsListItem> CREATOR = new Creator<NewsListItem>() {
        @Override
        public NewsListItem createFromParcel(Parcel in) {
            return new NewsListItem(in);
        }

        @Override
        public NewsListItem[] newArray(int size) {
            return new NewsListItem[size];
        }
    };
}
