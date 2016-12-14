// Generated code from Butter Knife. Do not modify!
package polus.ddns.net.chelinfo.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;
import polus.ddns.net.chelinfo.R;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  private View view2131492982;

  private View view2131492983;

  private View view2131492984;

  private View view2131492985;

  private View view2131492986;

  private View view2131492981;

  private View view2131492987;

  private View view2131492977;

  private View view2131492976;

  private View view2131492978;

  @UiThread
  public MainActivity_ViewBinding(final T target, View source) {
    this.target = target;

    View view;
    target.pogoda = Utils.findRequiredViewAsType(source, R.id.pogoda, "field 'pogoda'", WebView.class);
    target.prognoz = Utils.findRequiredViewAsType(source, R.id.prognoz, "field 'prognoz'", WebView.class);
    target.schoolText = Utils.findRequiredViewAsType(source, R.id.school_text, "field 'schoolText'", TextView.class);
    view = Utils.findRequiredView(source, R.id.button_centralny, "method 'showCentralny'");
    view2131492982 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showCentralny();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kalininsky, "method 'showKalininsky'");
    view2131492983 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKalininsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_kurchatovsky, "method 'showKurchatovsky'");
    view2131492984 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showKurchatovsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_leninsky, "method 'showLeninsky'");
    view2131492985 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showLeninsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_metallurgichesky, "method 'showMetallurgichesky'");
    view2131492986 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showMetallurgichesky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_sovetsky, "method 'showSovetsky'");
    view2131492981 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showSovetsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_traktorozavodsky, "method 'showTraktorozavodsky'");
    view2131492987 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.showTraktorozavodsky();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_center, "method 'loadCenterPrognoz'");
    view2131492977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.loadCenterPrognoz();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_north, "method 'loadNorthPrognoz'");
    view2131492976 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.loadNorthPrognoz();
      }
    });
    view = Utils.findRequiredView(source, R.id.button_south, "method 'loadSouthPrognoz'");
    view2131492978 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.loadSouthPrognoz();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.pogoda = null;
    target.prognoz = null;
    target.schoolText = null;

    view2131492982.setOnClickListener(null);
    view2131492982 = null;
    view2131492983.setOnClickListener(null);
    view2131492983 = null;
    view2131492984.setOnClickListener(null);
    view2131492984 = null;
    view2131492985.setOnClickListener(null);
    view2131492985 = null;
    view2131492986.setOnClickListener(null);
    view2131492986 = null;
    view2131492981.setOnClickListener(null);
    view2131492981 = null;
    view2131492987.setOnClickListener(null);
    view2131492987 = null;
    view2131492977.setOnClickListener(null);
    view2131492977 = null;
    view2131492976.setOnClickListener(null);
    view2131492976 = null;
    view2131492978.setOnClickListener(null);
    view2131492978 = null;

    this.target = null;
  }
}
