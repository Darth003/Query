package com.devdefiance.android.query.utils;

import com.devdefiance.android.query.Objects.SourceModel;
import com.devdefiance.android.query.R;

/**
 * Created by darth on 1/9/18.
 */

public class AppConstants {

    public static final int MAX_SEARCH_STRING_LENGTH = 32;
    public static final int SPEECH_INPUT_REQUEST = 10;

    // constant for the sources that can be searched
    public static final SourceModel[] sourceModels = {
            new SourceModel("Apps", R.drawable.ic_image_24dp),
            new SourceModel("Calendar", R.drawable.ic_image_24dp),
            new SourceModel("Contacts", R.drawable.ic_image_24dp),
            new SourceModel("Documents", R.drawable.ic_image_24dp),
            new SourceModel("Drive", R.drawable.ic_image_24dp),
            new SourceModel("Dropbox", R.drawable.ic_image_24dp),
            new SourceModel("Evernote", R.drawable.ic_image_24dp),
            new SourceModel("Gmail", R.drawable.ic_image_24dp),
            new SourceModel("Media", R.drawable.ic_image_24dp),
            new SourceModel("Messages", R.drawable.ic_image_24dp),
            new SourceModel("Pocket", R.drawable.ic_image_24dp),
            new SourceModel("Youtube", R.drawable.ic_image_24dp)
    };

}
