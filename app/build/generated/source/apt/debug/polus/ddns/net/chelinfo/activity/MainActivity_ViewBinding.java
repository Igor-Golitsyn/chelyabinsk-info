// Generated code from Butter Knife. Do not modify!
package polus.ddns.net.chelinfo.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import polus.ddns.net.chelinfo.R;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131558525;

  private View view2131558518;

  private View view2131558520;

  private View view2131558524;

  private View view2131558522;

  private View view2131558523;

  private View view2131558519;

  private View view2131558521;

  private View view2131558526;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.pogoda = Utils.findRequiredViewAsType(source, R.id.pogoda, "field 'pogoda'", WebView.class);
    target.schoolText = Utils.findRequiredViewAsType(source, R.id.school_text, "field 'schoolText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_news, "field 'buttonNews' and method 'showNews'");
    target.buttonNews = Utils.castView(view, R.id.button_news, "field 'buttonNews'", Button.class);
    view2131558525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showNews();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_centralny, "method 'showCentralny'");
    view2131558518 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCentralny();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kalininsky, "method 'showKalininsky'");
    view2131558520 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKalininsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kurchatovsky, "method 'showKurchatovsky'");
    view2131558524 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKurchatovsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_leninsky, "method 'showLeninsky'");
    view2131558522 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showLeninsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_metallurgichesky, "method 'showMetallurgichesky'");
    view2131558523 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showMetallurgichesky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_sovetsky, "method 'showSovetsky'");
    view2131558519 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSovetsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_traktorozavodsky, "method 'showTraktorozavodsky'");
    view2131558521 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showTraktorozavodsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_request, "method 'request'");
    view2131558526 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.request();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.pogoda = null;
    target.schoolText = null;
    target.buttonNews = null;

    view2131558525.setOnClickListener(null);
    view2131558525 = null;
    view2131558518.setOnClickListener(null);
    view2131558518 = null;
    view2131558520.setOnClickListener(null);
    view2131558520 = null;
    view2131558524.setOnClickListener(null);
    view2131558524 = null;
    view2131558522.setOnClickListener(null);
    view2131558522 = null;
    view2131558523.setOnClickListener(null);
    view2131558523 = null;
    view2131558519.setOnClickListener(null);
    view2131558519 = null;
    view2131558521.setOnClickListener(null);
    view2131558521 = null;
    view2131558526.setOnClickListener(null);
    view2131558526 = null;

    this.target = null;
  }
}
