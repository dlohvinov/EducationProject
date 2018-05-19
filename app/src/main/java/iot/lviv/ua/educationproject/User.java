package iot.lviv.ua.educationproject;


import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;


import com.google.firebase.database.Exclude;

public class User implements Parcelable {

    private String displayName;
    private String email;
    private String uid;
    private String rights;
    private Uri photoUri;
    private Boolean isAllowed;

    public static final String EDUCATOR_TOKEN = "EDUCATOR";
    public static final String STUDENT_TOKEN = "STUDENT";

    public User(){}

    public User(String displayName, String email, String uid) {
        this.displayName = displayName;
        this.email = email;
        this.uid = uid;
        this.isAllowed = false;
    }

    protected User(Parcel source) {
        this.displayName = source.readString();
        this.email = source.readString();
        this.uid = source.readString();
        this.rights = source.readString();
        this.isAllowed = source.readByte() != 0;
        this.photoUri = ((Uri) source.readValue(Uri.class.getClassLoader()));

    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[0];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.displayName);
        dest.writeString(this.email);
        dest.writeString(this.rights);
        dest.writeString(this.uid);
        dest.writeByte((byte) (this.isAllowed ? 1 : 0));
        dest.writeValue(this.photoUri);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getUid() {
        return uid;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public Boolean getIsAllowed() {
        return isAllowed;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }
}
