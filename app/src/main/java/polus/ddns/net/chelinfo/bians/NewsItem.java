package polus.ddns.net.chelinfo.bians;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by golit on 02.08.2017.
 */

public class NewsItem implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("seeders")
    @Expose
    private int seeders;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("date")
    @Expose
    private long date;

    protected NewsItem(Parcel in){
        name=in.readString();
        link=in.readString();
        seeders=in.readInt();
        size=in.readString();
        date=in.readLong();
    }

    public NewsItem(String name, String link, int seeders, String size, long date) {
        this.name = name;
        this.link = link;
        this.seeders = seeders;
        this.size = size;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public int getSeeders() {
        return seeders;
    }

    public String getSize() {
        return size;
    }

    public long getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", seeders=" + seeders +
                ", size='" + size + '\'' +
                ", date=" + date +
                '}';
    }

    public static final Creator<NewsItem> CREATOR = new Creator<NewsItem>() {
        @Override
        public NewsItem createFromParcel(Parcel in) {
            return new NewsItem(in);
        }

        @Override
        public NewsItem[] newArray(int size) {
            return new NewsItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(link);
        dest.writeInt(seeders);
        dest.writeString(size);
        dest.writeLong(date);
    }
}
