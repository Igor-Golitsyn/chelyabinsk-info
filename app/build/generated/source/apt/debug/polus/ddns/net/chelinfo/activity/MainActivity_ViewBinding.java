// Generated code from Butter Knife. Do not modify!
package polus.ddns.net.chelinfo.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

  private View view2131230764;

  private View view2131230780;

  private View view2131230885;

  private View view2131230823;

  private View view2131230759;

  private View view2131230760;

  private View view2131230761;

  private View view2131230762;

  private View view2131230763;

  private View view2131230766;

  private View view2131230767;

  private View view2131230765;

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
    target.school_text_header = Utils.findRequiredViewAsType(source, R.id.school_text_header, "field 'school_text_header'", TextView.class);
    target.chelAdminText = Utils.findRequiredViewAsType(source, R.id.chelAdminText, "field 'chelAdminText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_news, "field 'buttonNews' and method 'showNews'");
    target.buttonNews = Utils.castView(view, R.id.button_news, "field 'buttonNews'", Button.class);
    view2131230764 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showNews();
      }
    });
    target.searchVodaText = Utils.findRequiredViewAsType(source, R.id.search_voda_text, "field 'searchVodaText'", EditText.class);
    target.progressBar = Utils.findRequiredViewAsType(source, R.id.progressBar, "field 'progressBar'", ProgressBar.class);
    view = Utils.findRequiredView(source, R.id.chelAdminPic, "field 'chelAdminPic' and method 'onClickChelAdminPic'");
    target.chelAdminPic = Utils.castView(view, R.id.chelAdminPic, "field 'chelAdminPic'", ImageView.class);
    view2131230780 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickChelAdminPic();
      }
    });
    view = Utils.findRequiredView(source, R.id.search_voda_image, "method 'showVoda'");
    view2131230885 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showVoda();
      }
    });
    view = Utils.findRequiredView(source, R.id.image_voda, "method 'showSite'");
    view2131230823 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSite();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_centralny, "method 'showCentralny'");
    view2131230759 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCentralny();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kalininsky, "method 'showKalininsky'");
    view2131230760 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKalininsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kurchatovsky, "method 'showKurchatovsky'");
    view2131230761 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKurchatovsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_leninsky, "method 'showLeninsky'");
    view2131230762 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showLeninsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_metallurgichesky, "method 'showMetallurgichesky'");
    view2131230763 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showMetallurgichesky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_sovetsky, "method 'showSovetsky'");
    view2131230766 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSovetsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_traktorozavodsky, "method 'showTraktorozavodsky'");
    view2131230767 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showTraktorozavodsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_request, "method 'request'");
    view2131230765 = view;
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
    target.school_text_header = null;
    target.chelAdminText = null;
    target.buttonNews = null;
    target.searchVodaText = null;
    target.progressBar = null;
    target.chelAdminPic = null;

    view2131230764.setOnClickListener(null);
    view2131230764 = null;
    view2131230780.setOnClickListener(null);
    view2131230780 = null;
    view2131230885.setOnClickListener(null);
    view2131230885 = null;
    view2131230823.setOnClickListener(null);
    view2131230823 = null;
    view2131230759.setOnClickListener(null);
    view2131230759 = null;
    view2131230760.setOnClickListener(null);
    view2131230760 = null;
    view2131230761.setOnClickListener(null);
    view2131230761 = null;
    view2131230762.setOnClickListener(null);
    view2131230762 = null;
    view2131230763.setOnClickListener(null);
    view2131230763 = null;
    view2131230766.setOnClickListener(null);
    view2131230766 = null;
    view2131230767.setOnClickListener(null);
    view2131230767 = null;
    view2131230765.setOnClickListener(null);
    view2131230765 = null;
  }
}
