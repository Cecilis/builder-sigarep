package sigarep.herramientas;

import java.security.GeneralSecurityException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class EnviarCorreo {

	private final Properties properties = new Properties();

	private final String username = "sigarep_ucla@cacei.com.ve";

	private final String password = "sigarep_ucla1";

	private Session session;

	private void init() {
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.socketFactory", sf);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.host", "mail.cacei.com.ve");
		properties.put("mail.smtp.port", "26");
		properties.put("mail.smtp.debug", "true");
		properties.put("mail.smtp.mail.sender", "sigarep_ucla@cacei.com.ve");
		session = Session.getInstance(properties,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
	}

	// funci�n para recuperar la contrase�a
	public void sendEmail(String correoReceptor, String clave) {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Recuperaci�n de clave de acceso al sistema SIGAREP");
			message.setText("La Recuperaci�n de contrase�a se realiz� "
					+ "satisfactoriamente."
					+ "\n\n SIRAGEP le informa que su contrase�a para acceder a "
					+ "nuestro sistema es: "
					+ clave
					+ "\n\n\t\t Sistema de Informaci�n "
					+ "para el Apoyo a la Gesti�n del Regimen de Repitencia y Permanencia");
			Transport.send(message);
			System.out.println("Enviado");
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}

	// funci�n para recuperar la contrase�a
	public void sendEmailWelcomeToSigarep(String correoReceptor,
			String nombreUsuario, String clave) {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					correoReceptor));
			message.setSubject("Bienvenida al sistema SIGAREP - Envio de login y password de usuario autorizado");
			message.setText("Usted ha sido autorizado para acceder al sistema "
					+ "integrado para el Apoyo a la Gesti�n del Regimen de Repitencia y Permanencia"
					+ "\n\n ingresando con login: " + nombreUsuario + " y "
					+ "password: " + clave + "\n\n\t\t" + "");
			Transport.send(message);
			System.out.println("Enviado");
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}

	// Funci�n para contactanos
	public void sendEmailContactanos(String correo, String nombre,
			String telefono, String consulta) {
		init();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress((String) properties
					.get("mail.smtp.mail.sender")));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));
			message.setSubject("Mensaje o consulta enviado por: " + nombre);
			message.setText(nombre + ", "
					+ "\nN�mero de tel�fono: " + telefono
					+ "\nCorreo: " + correo
					+ "\nHa enviado la siguiente consulta: " + "\n\n"
					+ consulta + "\n\n\t\t" + "");
			Transport.send(message);
		} catch (MessagingException me) {
			throw new RuntimeException(me);
		}
	}
}
