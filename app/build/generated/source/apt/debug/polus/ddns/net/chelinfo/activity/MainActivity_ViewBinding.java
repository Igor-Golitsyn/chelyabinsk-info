// Generated code from Butter Knife. Do not modify!
package polus.ddns.net.chelinfo.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import polus.ddns.net.chelinfo.R;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230756;

  private View view2131230866;

  private View view2131230811;

  private View view2131230751;

  private View view2131230752;

  private View view2131230753;

  private View view2131230754;

  private View view2131230755;

  private View view2131230758;

  private View view2131230759;

  private View view2131230757;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    target.pogoda = Utils.findRequiredViewAsType(source, R.id.pogoda, "field 'pogoda'", WebView.class);
    target.schoolText = Utils.findRequiredViewAsType(source, R.id.school_text, "field 'schoolText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_news, "field 'buttonNews' and method 'showNews'");
    target.buttonNews = Utils.castView(view, R.id.button_news, "field 'buttonNews'", Button.class);
    view2131230756 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showNews();
      }
    });
    target.searchVodaText = Utils.findRequiredViewAsType(source, R.id.search_voda_text, "field 'searchVodaText'", EditText.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.search_voda_image, "method 'showVoda'");
    view2131230866 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showVoda();
      }
    });
    view = Utils.findRequiredView(source, R.id.image_voda, "method 'showSite'");
    view2131230811 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSite();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_centralny, "method 'showCentralny'");
    view2131230751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCentralny();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kalininsky, "method 'showKalininsky'");
    view2131230752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKalininsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kurchatovsky, "method 'showKurchatovsky'");
    view2131230753 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKurchatovsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_leninsky, "method 'showLeninsky'");
    view2131230754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showLeninsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_metallurgichesky, "method 'showMetallurgichesky'");
    view2131230755 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showMetallurgichesky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_sovetsky, "method 'showSovetsky'");
    view2131230758 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSovetsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_traktorozavodsky, "method 'showTraktorozavodsky'");
    view2131230759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showTraktorozavodsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_request, "method 'request'");
    view2131230757 = view;
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
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pogoda = null;
    target.schoolText = null;
    target.buttonNews = null;
    target.searchVodaText = null;
    target.progressBar = null;

    view2131230756.setOnClickListener(null);
    view2131230756 = null;
    view2131230866.setOnClickListener(null);
    view2131230866 = null;
    view2131230811.setOnClickListener(null);
    view2131230811 = null;
    view2131230751.setOnClickListener(null);
    view2131230751 = null;
    view2131230752.setOnClickListener(null);
    view2131230752 = null;
    view2131230753.setOnClickListener(null);
    view2131230753 = null;
    view2131230754.setOnClickListener(null);
    view2131230754 = null;
    view2131230755.setOnClickListener(null);
    view2131230755 = null;
    view2131230758.setOnClickListener(null);
    view2131230758 = null;
    view2131230759.setOnClickListener(null);
    view2131230759 = null;
    view2131230757.setOnClickListener(null);
    view2131230757 = null;
  }
}
