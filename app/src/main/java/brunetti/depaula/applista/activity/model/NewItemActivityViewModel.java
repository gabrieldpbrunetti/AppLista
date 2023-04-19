package brunetti.depaula.applista.activity.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    Uri selectPhotoLocation = null;

    public Uri getSelectedPhotoLocation(){
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
