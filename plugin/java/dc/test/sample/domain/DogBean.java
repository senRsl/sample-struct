package dc.test.sample.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 独立module
 *
 * @author senrsl
 * @ClassName: DogBean
 * @Package: dc.test.sample.domain
 * @CreateTime: 2018/11/19 5:26 PM
 */
public class DogBean implements Parcelable {
    private String nameEng;
    private String nameChinese;
    private String from;

    public DogBean() {
    }

    public DogBean(String nameEng, String nameChinese, String from) {
        this.nameEng = nameEng;
        this.nameChinese = nameChinese;
        this.from = from;
    }

    public String getNameEng() {
        return nameEng;
    }

    public String getNameChinese() {
        return nameChinese;
    }

    public String getFrom() {
        return from;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setNameChinese(String nameChinese) {
        this.nameChinese = nameChinese;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "DogBean{" +
                "nameEng='" + nameEng + '\'' +
                ", nameChinese='" + nameChinese + '\'' +
                ", from='" + from + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameEng);
        dest.writeString(nameChinese);
        dest.writeString(from);
    }

    public static final Parcelable.Creator<DogBean> CREATOR = new Creator<DogBean>() {
        @Override
        public DogBean createFromParcel(Parcel source) {
            return new DogBean(source.readString(), source.readString(), source.readString());
        }

        @Override
        public DogBean[] newArray(int size) {
            return new DogBean[size];
        }
    };
}
