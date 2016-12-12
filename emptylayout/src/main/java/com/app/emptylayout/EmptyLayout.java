package com.app.emptylayout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EmptyLayout extends LinearLayout {

    private Animation mLoadingAnimation;

    private ViewGroup mLoadingView;
    private ViewGroup mEmptyView;
    private ViewGroup mErrorView;

    private View loadingViewButton;
    private View emptyViewButton;
    private View errorViewButton;

    private TextView mLoadingMessageView;
    private TextView mEmptyMessageView;
    private TextView mErrorMessageView;


    private RelativeLayout mEmptyRelativeLayout;
    private int mErrorMessageViewId;
    private int mEmptyMessageViewId;
    private int mLoadingMessageViewId;
    private LayoutInflater mInflater;

    private boolean mViewsAdded; // flag for whether mEmptyRelativeLayout has been initialized.
    private boolean mEmptyViewAdded;
    private boolean mErrorViewAdded;
    private boolean mLoadingViewAdded;

    private int mLoadingAnimationViewId;
    private View loadingAnimationView;

    private OnClickListener mLoadingButtonClickListener;
    private OnClickListener mEmptyButtonClickListener;
    private OnClickListener mErrorButtonClickListener;


    /**
     * The empty state
     */
    public final static int TYPE_EMPTY = 1;
    /**
     * The loading state
     */
    public final static int TYPE_LOADING = 2;
    /**
     * The error state
     */
    public final static int TYPE_ERROR = 3;

    // ---------------------------
    // default values
    // ---------------------------
    private int mEmptyType = TYPE_LOADING;
    private int mErrorDrawable = R.drawable.ic_error;
    private int mEmptyDrawable = R.drawable.ic_empty;

    private String mErrorMessage = getResources().getString(R.string.error_message);
    private String mEmptyMessage = getResources().getString(R.string.empty_message);
    private String mLoadingMessage = getResources().getString(R.string.loading_message);

    private int mLoadingViewButtonId = R.id.buttonLoading;
    private int mErrorViewButtonId = R.id.buttonError;
    private int mEmptyViewButtonId = R.id.buttonEmpty;
    private boolean mShowEmptyButton = true;
    private boolean mShowLoadingButton = true;
    private boolean mShowErrorButton = true;

    private List<View> childViews;


    public EmptyLayout(Context context) {
        super(context);
        init();
    }


    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EmptyLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        childViews = new ArrayList<>();
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Gets the loading layout
     *
     * @return the loading layout
     */
    public ViewGroup getLoadingView() {
        return mLoadingView;
    }

    /**
     * Sets loading layout
     *
     * @param loadingView
     */
    public void setLoadingView(ViewGroup loadingView) {
        this.mLoadingView = loadingView;
    }

    /**
     * Sets loading layout resource
     *
     * @param res
     */
    public void setLoadingViewRes(int res) {
        this.mLoadingView = (ViewGroup) mInflater.inflate(res, null);
    }

    /**
     * Gets the empty layout
     *
     * @return the empty layout
     */
    public ViewGroup getEmptyView() {
        return mEmptyView;
    }

    /**
     * Sets empty layout
     *
     * @param emptyView
     */
    public void setEmptyView(ViewGroup emptyView) {
        this.mEmptyView = emptyView;
    }

    /**
     * Sets empty layout resource
     *
     * @param res
     */
    public void setEmptyViewRes(int res) {
        this.mEmptyView = (ViewGroup) mInflater.inflate(res, null);
    }

    /**
     * Gets the error layout
     *
     * @return the error layout
     */
    public ViewGroup getErrorView() {
        return mErrorView;
    }

    /**
     * Sets error layout
     *
     * @param errorView
     */
    public void setErrorView(ViewGroup errorView) {
        this.mErrorView = errorView;
    }

    /**
     * Sets error layout resource
     *
     * @param res
     */
    public void setErrorViewRes(int res) {
        this.mErrorView = (ViewGroup) mInflater.inflate(res, null);
    }

    /**
     * Gets the loading animation
     *
     * @return the loading animation
     */
    public Animation getLoadingAnimation() {
        return mLoadingAnimation;
    }

    /**
     * Sets the loading animation
     *
     * @param animation
     */
    public void setLoadingAnimation(Animation animation) {
        this.mLoadingAnimation = animation;
    }

    /**
     * Sets the resource of loading animation
     *
     * @param animationResource
     */
    public void setLoadingAnimationRes(int animationResource) {
        mLoadingAnimation = AnimationUtils.loadAnimation(getContext(), animationResource);
    }


    /**
     * @return
     */
    public int getEmptyType() {
        return mEmptyType;
    }

    /**
     * @param emptyType
     */
    public void setEmptyType(int emptyType) {
        this.mEmptyType = emptyType;
        changeType();
    }

    /**
     * @return
     */
    public String getErrorMessage() {
        return mErrorMessage;
    }

    /**
     * @param errorMessage  the error message
     * @param messageViewId
     */
    public void setErrorMessage(String errorMessage, int messageViewId) {
        this.mErrorMessage = errorMessage;
        this.mErrorMessageViewId = messageViewId;
    }

    /**
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.mErrorMessage = errorMessage;
    }

    /**
     * @return
     */
    public String getEmptyMessage() {
        return mEmptyMessage;
    }

    /**
     * @param emptyMessage  the message
     * @param messageViewId
     */
    public void setEmptyMessage(String emptyMessage, int messageViewId) {
        this.mEmptyMessage = emptyMessage;
        this.mEmptyMessageViewId = messageViewId;
    }

    /**
     * @param emptyMessage the message
     */
    public void setEmptyMessage(String emptyMessage) {
        this.mEmptyMessage = emptyMessage;
    }

    /**
     * Gets the message which will be shown when the list is being loaded
     *
     * @return
     */
    public String getLoadingMessage() {
        return mLoadingMessage;
    }

    /**
     * @param loadingMessage the message
     * @param messageViewId
     */
    public void setLoadingMessage(String loadingMessage, int messageViewId) {
        this.mLoadingMessage = loadingMessage;
        this.mLoadingMessageViewId = messageViewId;
    }

    /**
     * @param loadingMessage the message
     */
    public void setLoadingMessage(String loadingMessage) {
        this.mLoadingMessage = loadingMessage;
    }

    /**
     * @return
     */
    public int getLoadingAnimationViewId() {
        return mLoadingAnimationViewId;
    }

    /**
     * @param loadingAnimationViewId the id of the view
     */
    public void setLoadingAnimationViewId(int loadingAnimationViewId) {
        this.mLoadingAnimationViewId = loadingAnimationViewId;
    }

    /**
     * Gets the OnClickListener which perform when LoadingView was click
     *
     * @return
     */
    public OnClickListener getLoadingButtonClickListener() {
        return mLoadingButtonClickListener;
    }

    /**
     * Sets the OnClickListener to LoadingView
     *
     * @param loadingButtonClickListener OnClickListener Object
     */
    public void setLoadingButtonClickListener(OnClickListener loadingButtonClickListener) {
        this.mLoadingButtonClickListener = loadingButtonClickListener;
    }

    /**
     * Gets the OnClickListener which perform when EmptyView was click
     *
     * @return
     */
    public OnClickListener getEmptyButtonClickListener() {
        return mEmptyButtonClickListener;
    }

    /**
     * Sets the OnClickListener to EmptyView
     *
     * @param emptyButtonClickListener OnClickListener Object
     */
    public void setEmptyButtonClickListener(OnClickListener emptyButtonClickListener) {
        this.mEmptyButtonClickListener = emptyButtonClickListener;
    }

    /**
     * Gets the OnClickListener which perform when ErrorView was click
     *
     * @return
     */
    public OnClickListener getErrorButtonClickListener() {
        return mErrorButtonClickListener;
    }

    /**
     * Sets the OnClickListener to ErrorView
     *
     * @param errorButtonClickListener OnClickListener Object
     */
    public void setErrorButtonClickListener(OnClickListener errorButtonClickListener) {
        this.mErrorButtonClickListener = errorButtonClickListener;
    }

    /**
     * Gets if a button is shown in the empty view
     *
     * @return if a button is shown in the empty view
     */
    public boolean isEmptyButtonShown() {
        return mShowEmptyButton;
    }

    /**
     * Sets if a button will be shown in the empty view
     *
     * @param showEmptyButton will a button be shown in the empty view
     */
    public void setShowEmptyButton(boolean showEmptyButton) {
        this.mShowEmptyButton = showEmptyButton;
    }

    /**
     * Gets if a button is shown in the loading view
     *
     * @return if a button is shown in the loading view
     */
    public boolean isLoadingButtonShown() {
        return mShowLoadingButton;
    }

    /**
     * Sets if a button will be shown in the loading view
     *
     * @param showLoadingButton will a button be shown in the loading view
     */
    public void setShowLoadingButton(boolean showLoadingButton) {
        this.mShowLoadingButton = showLoadingButton;
    }

    /**
     * Gets if a button is shown in the error view
     *
     * @return if a button is shown in the error view
     */
    public boolean isErrorButtonShown() {
        return mShowErrorButton;
    }

    /**
     * Sets if a button will be shown in the error view
     *
     * @param showErrorButton will a button be shown in the error view
     */
    public void setShowErrorButton(boolean showErrorButton) {
        this.mShowErrorButton = showErrorButton;
    }

    /**
     * Gets the ID of the button in the loading view
     *
     * @return the ID of the button in the loading view
     */
    public int getmLoadingViewButtonId() {
        return mLoadingViewButtonId;
    }

    /**
     * Sets the ID of the button in the loading view. This ID is required if you want the button the loading view to be click-able.
     *
     * @param loadingViewButtonId the ID of the button in the loading view
     */
    public void setLoadingViewButtonId(int loadingViewButtonId) {
        this.mLoadingViewButtonId = loadingViewButtonId;
    }

    /**
     * Gets the ID of the button in the error view
     *
     * @return the ID of the button in the error view
     */
    public int getErrorViewButtonId() {
        return mErrorViewButtonId;
    }

    /**
     * Sets the ID of the button in the error view. This ID is required if you want the button the error view to be click-able.
     *
     * @param errorViewButtonId the ID of the button in the error view
     */
    public void setErrorViewButtonId(int errorViewButtonId) {
        this.mErrorViewButtonId = errorViewButtonId;
    }

    /**
     * Gets the ID of the button in the empty view
     *
     * @return the ID of the button in the empty view
     */
    public int getEmptyViewButtonId() {
        return mEmptyViewButtonId;
    }

    /**
     * Sets the ID of the button in the empty view. This ID is required if you want the button the empty view to be click-able.
     *
     * @param emptyViewButtonId the ID of the button in the empty view
     */
    public void setEmptyViewButtonId(int emptyViewButtonId) {
        this.mEmptyViewButtonId = emptyViewButtonId;
    }


    public int getErrorDrawable() {
        return mErrorDrawable;
    }

    /**
     * 设置错误的图片
     *
     * @param mErrorDrawable 错误图片资源id
     */
    public void setErrorDrawable(int mErrorDrawable) {
        this.mErrorDrawable = mErrorDrawable;
    }

    public int getEmptyDrawable() {
        return mEmptyDrawable;
    }

    /**
     * 设置空的图片
     *
     * @param mEmptyDrawable 错误空资源id
     */
    public void setEmptyDrawable(int mEmptyDrawable) {
        this.mEmptyDrawable = mEmptyDrawable;
    }

    private void getChildViews() {
        int childCount = getChildCount();
        Log.d("EmptyLayout", "ChildCount:" + childCount);
        View view;
        childViews.clear();
        for (int i = 0; i < childCount; i++) {
            view = getChildAt(i);
            if (isEmptyView(view)) {
                continue;
            }
            childViews.add(view);
        }
    }

    private void hideChildView() {
        for (View view : childViews) {
            if (isEmptyView(view)) {
                continue;
            }
            view.setVisibility(GONE);
        }
    }

    /**
     * 判断view 对象是否是EmptyView
     *
     * @param view
     * @return
     */
    private boolean isEmptyView(View view) {
        if ((view == null || mEmptyRelativeLayout == view || view == mLoadingView || view == mEmptyView || view == mErrorView)) {
            return true;
        }
        return false;
    }

    private void showChildView() {
        for (View view : childViews) {
            if (isEmptyView(view)) {
                continue;
            }
            view.setVisibility(VISIBLE);
        }
    }

    /**
     * 隐藏EmptyView
     */
    private void hideAllView() {
        if (mLoadingView != null) {
            mLoadingView.setVisibility(GONE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(GONE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(GONE);
        }
    }

    /**
     * 展示错误信息
     *
     * @param resId 图片资源id
     * @param text
     */
    public void showError(int resId, String text) {
        setErrorDrawable(resId);
        setEmptyMessage(text);
        showError();
    }

    public void showError() {
        getChildViews();
        hideChildView();
        this.mEmptyType = TYPE_ERROR;
        changeType();
    }


    /**
     * 展示空信息
     *
     * @param resId 图片资源id
     * @param text
     */
    public void showEmpty(int resId, String text) {

        setEmptyDrawable(resId);
        setEmptyMessage(text);
        showEmpty();
    }

    public void showEmpty() {

        getChildViews();
        hideChildView();
        this.mEmptyType = TYPE_EMPTY;
        changeType();

    }

    /**
     * 展示加载中
     *
     * @param text
     */
    public void showLoading(String text) {
        setLoadingMessage(text);
        showLoading();
    }

    public void showLoading() {
        getChildViews();
        hideChildView();
        this.mEmptyType = TYPE_LOADING;
        changeType();
    }

    /**
     * 隐藏EmptyLayout
     */
    public void hide() {
        showChildView();
        hideAllView();
    }


    private void changeType() {

        setDefaultValues();
        refreshMessages();

        // insert views in the root view
        if (!mViewsAdded) {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            lp.addRule(RelativeLayout.CENTER_IN_PARENT);
            mEmptyRelativeLayout = new RelativeLayout(getContext());
            mEmptyRelativeLayout.setGravity(Gravity.CENTER);
            mEmptyRelativeLayout.setLayoutParams(lp);
            mViewsAdded = true;
            mEmptyRelativeLayout.setVisibility(VISIBLE);
            addView(mEmptyRelativeLayout);
        }

        switch (mEmptyType) {
            case TYPE_EMPTY:
                if (!mEmptyViewAdded) {
                    if (mEmptyView != null) mEmptyRelativeLayout.addView(mEmptyView);
                    mEmptyViewAdded = true;
                }
                break;
            case TYPE_ERROR:
                if (!mErrorViewAdded) {
                    if (mErrorView != null) mEmptyRelativeLayout.addView(mErrorView);
                    mErrorViewAdded = true;
                }
                break;
            case TYPE_LOADING:
                if (!mLoadingViewAdded) {
                    if (mLoadingView != null) mEmptyRelativeLayout.addView(mLoadingView);
                    mLoadingViewAdded = true;
                }
                break;
            default:
                break;
        }

        // change empty type
        loadingAnimationView = null;
        if (mLoadingAnimationViewId > 0) {
            loadingAnimationView = findViewById(mLoadingAnimationViewId);
        }
        switch (mEmptyType) {
            case TYPE_EMPTY:
                if (mEmptyView != null) mEmptyView.setVisibility(View.VISIBLE);
                if (mErrorView != null) mErrorView.setVisibility(View.GONE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(View.GONE);
                    if (loadingAnimationView != null && loadingAnimationView.getAnimation() != null)
                        loadingAnimationView.getAnimation().cancel();
                }
                break;
            case TYPE_ERROR:
                if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);
                if (mErrorView != null) mErrorView.setVisibility(View.VISIBLE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(View.GONE);
                    if (loadingAnimationView != null && loadingAnimationView.getAnimation() != null)
                        loadingAnimationView.getAnimation().cancel();
                }
                break;
            case TYPE_LOADING:
                if (mEmptyView != null) mEmptyView.setVisibility(View.GONE);
                if (mErrorView != null) mErrorView.setVisibility(View.GONE);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(View.VISIBLE);
                    if (mLoadingAnimation != null && loadingAnimationView != null) {
                        loadingAnimationView.startAnimation(mLoadingAnimation);
                    } else if (loadingAnimationView != null) {
                        loadingAnimationView.startAnimation(getRotateAnimation());
                    }
                }
                break;
            default:
                break;
        }
    }

    private void refreshMessages() {
        switch (this.mEmptyType) {
            case TYPE_EMPTY:
                if (mEmptyMessageViewId > 0 && mEmptyMessage != null && mEmptyView != null) {
                    mEmptyMessageView = ((TextView) mEmptyView.findViewById(mEmptyMessageViewId));
                    mEmptyMessageView.setText(mEmptyMessage);
                    setTopDrawables(mEmptyMessageView, mEmptyDrawable);
                }
                break;
            case TYPE_ERROR:
                if (mErrorMessageViewId > 0 && mErrorMessage != null && mErrorView != null) {
                    mErrorMessageView = ((TextView) mErrorView.findViewById(mErrorMessageViewId));
                    mErrorMessageView.setText(mErrorMessage);
                    setTopDrawables(mErrorMessageView, mErrorDrawable);
                }
                break;
            case TYPE_LOADING:
                if (mLoadingMessageViewId > 0 && mLoadingMessage != null && mLoadingView != null) {
                    mLoadingMessageView = ((TextView) mLoadingView.findViewById(mLoadingMessageViewId));
                    mLoadingMessageView.setText(mLoadingMessage);
                }
                break;
            default:
                throw new RuntimeException("EmptyLayout wrong type!");

        }
    }


    private void setTopDrawables(TextView textView, int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        textView.setCompoundDrawables(null, drawable, null, null);
    }

    private void setDefaultEmptyView() {
        if (mEmptyView == null) {
            mEmptyView = (ViewGroup) mInflater.inflate(R.layout.view_empty, null);
            if (!(mEmptyMessageViewId > 0)) mEmptyMessageViewId = R.id.textViewMessage;
            if (mShowEmptyButton && mEmptyViewButtonId > 0 && mEmptyButtonClickListener != null) {
                if (emptyViewButton == null) {
                    ViewStub buttonEmptyViewStub = (ViewStub) mEmptyView.findViewById(R.id.buttonEmptyViewStub);
                    buttonEmptyViewStub.inflate();
                    emptyViewButton = mEmptyView.findViewById(mEmptyViewButtonId);
                }
                if (emptyViewButton != null) {
                    emptyViewButton.setOnClickListener(mEmptyButtonClickListener);
                    emptyViewButton.setVisibility(View.VISIBLE);
                }
            } else if (mEmptyViewButtonId > 0 && emptyViewButton != null) {
                emptyViewButton.setVisibility(View.GONE);
            }
        }
    }

    private void setDefaultLoadingView() {
        if (mLoadingView == null) {
            mLoadingView = (ViewGroup) mInflater.inflate(R.layout.view_loading, null);
            mLoadingAnimationViewId = R.id.imageViewLoading;
            if (!(mLoadingMessageViewId > 0)) {
                mLoadingMessageViewId = R.id.textViewMessage;
            }
            if (mShowLoadingButton && mLoadingViewButtonId > 0 && mLoadingButtonClickListener != null) {
                if (loadingViewButton == null) {
                    ViewStub buttonLoadingViewStub = (ViewStub) mLoadingView.findViewById(R.id.buttonLoadingViewStub);
                    buttonLoadingViewStub.inflate();
                    loadingViewButton = mLoadingView.findViewById(mLoadingViewButtonId);
                }
                if (loadingViewButton != null) {
                    loadingViewButton.setOnClickListener(mLoadingButtonClickListener);
                    loadingViewButton.setVisibility(View.VISIBLE);
                }
            } else if (mLoadingViewButtonId > 0 && loadingViewButton != null) {
                loadingViewButton.setVisibility(View.GONE);
            }
        }
    }

    private void setDefaultErrorView() {
        if (mErrorView == null) {
            mErrorView = (ViewGroup) mInflater.inflate(R.layout.view_error, null);
            if (!(mErrorMessageViewId > 0)) mErrorMessageViewId = R.id.textViewMessage;
            if (mShowErrorButton && mErrorViewButtonId > 0 && mErrorButtonClickListener != null) {
                if (errorViewButton == null) {
                    ViewStub buttonErrorViewStub = (ViewStub) mErrorView.findViewById(R.id.buttonErrorViewStub);
                    buttonErrorViewStub.inflate();
                    errorViewButton = mErrorView.findViewById(mErrorViewButtonId);
                }
                if (errorViewButton != null) {
                    errorViewButton.setOnClickListener(mErrorButtonClickListener);
                    errorViewButton.setVisibility(View.VISIBLE);
                }
            } else if (mErrorViewButtonId > 0 && errorViewButton != null) {
                errorViewButton.setVisibility(View.GONE);
            }
        }
    }

    private void setDefaultValues() {
        switch (this.mEmptyType) {
            case TYPE_EMPTY:
                setDefaultEmptyView();
                break;
            case TYPE_ERROR:
                setDefaultErrorView();
                break;
            case TYPE_LOADING:
                setDefaultLoadingView();
                break;
            default:
                throw new RuntimeException("EmptyLayout wrong type!");
        }
    }

    private static Animation getRotateAnimation() {
        final RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        return rotateAnimation;
    }

}
