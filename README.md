## WholeRippleView
In the [feature discovery](https://material.io/guidelines/growth-communications/feature-discovery.html) section of Material Design Google introduced a nice way to draw the attention of the user to a specific item of a [NavigationDrawer](https://developer.android.com/training/implementing-navigation/nav-drawer.html). Unfortanatly the normal Ripple drawable from the Android SDK 21 is not suited for this behavior because the ripple does not extend to the bounds of the View.

This repository contains a custom RippleView which can be used to create an animation of NavigationDrawer items (and of course for all other Views). It was devopled based on the answers of [this](https://stackoverflow.com/questions/48034152/make-ripple-fill-whole-view) StackOverflow question and therefore is related to Robin Chutauxs [RippleEffect](https://github.com/traex/RippleEffect) library. It also shows the use of the View on a custom item for Mike Penzs [MaterialDrawer](https://github.com/mikepenz/MaterialDrawer) library.

The final result of the RippleView can be seen in [this video](https://storage.googleapis.com/material-design/publish/material_v_12/assets/0B4kv-3uZbS1qN21uRzN6OF9KQ00/useredu-er-featuredisc-motion-idle2.mp4).

### Contribution
Whenever you find bugs or have any optimizations fell free to send me a pull request.

### Requirements
The View can be used on Android SDK 11+
