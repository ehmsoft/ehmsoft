package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FullScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class About extends FullScreen {

	/**
	 * Pantalla Acerca de
	 */
	private String _licencia = "AVISO IMPORTANTE: Antes de descargar, instalar, copiar o utilizar "
			+ "el software lea los siguientes términos y condiciones: ehmSoftware es el propietario del "
			+ "software y del soporte físico. ehmSoftware  concede este software bajo una licencia "
			+ "exclusivamente de uso a una persona natural o jurídica. Disponiendo de ésta, la instalación podrá "
			+ "realizarse solamente en un dispositivo, el cual está permitido cambiarlo, para esto debe "
			+ "eliminar por completo la instalación en el dispositivo anterior y contactar a ehmSoftware. Para "
			+ "que el software funcione correctamente se debe contar con un sistema operativo BlackBerry® OS 5.0 "
			+ "o superior y una tarjeta microSD presente en el dispositivo, ehmSoftware no se hará responsable "
			+ "por pérdidas, daños, reclamaciones o costes de cualquier naturaleza, incluyendo cualquier daño "
			+ "consecuente, indirecto o secundario, ni de cualquier pérdida de beneficios o ganancias, daños "
			+ "que resulten de la interrupción del negocio, daño personal o incumplimiento de cualquier deber "
			+ "de diligencia o reclamaciones de terceros, del mismo modo no garantiza que el software este "
			+ "libre de errores y se recomienda que en el caso de que éstos se presente sean reportados a soporte@ehmsoft.com "
			+ "para realizar el seguimiento de los mismos y tomar medidas para su corrección en el menor tiempo "
			+ "posible. ehmSoftware se reserva todos los derechos que no se le conceden expresamente a usted en "
			+ "este documento y puede retirar la licencia de uso si usted no cumple con los términos y condiciones. ";
	private BitmapField _logo;
	private final String NOMBRE_APLICACION = "Procesos Judiciales";
	private final String VERSION = "Versión 1.0";
	private final String LICENCIA = "Ver Términos y condiciones";
	private final String EMPRESA = "ehmSoft";
	private final String URL = "www.ehmsoft.com";
	private final String CONTACTO = "soporte@ehmsoft.com";

	public About() {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		this.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		_logo = new BitmapField(Bitmap.getBitmapResource("logo.png"),
				FIELD_HCENTER);
		WhiteLabelField lblLicencia = new WhiteLabelField(LICENCIA,
				Field.FIELD_HCENTER | Field.FOCUSABLE) {
			protected boolean navigationClick(int status, int time) {
				Dialog.inform(_licencia);
				return true;
			}
		};

		add(new WhiteLabelField("\n", Field.FIELD_HCENTER));
		add(_logo);
		add(new WhiteLabelField(NOMBRE_APLICACION, Field.FIELD_HCENTER));
		add(new WhiteLabelField(VERSION, Field.FIELD_HCENTER));
		add(lblLicencia);
		add(new WhiteLabelField(EMPRESA, Field.FIELD_HCENTER));
		add(new WhiteLabelField(URL, Field.FOCUSABLE | Field.FIELD_HCENTER));
		add(new WhiteLabelField(CONTACTO, Field.FOCUSABLE | Field.FIELD_HCENTER));

	}

	public boolean onClose() {
		Util.popScreen(this);
		return true;
	}

	protected class WhiteLabelField extends LabelField {
		public WhiteLabelField(String texto, long style) {
			super(texto, style);
		}

		public void paint(Graphics graphics) {
			graphics.setColor(Color.WHITE);
			super.paint(graphics);
		}
	}
}
