package pe.idat.frontend.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.button.MaterialButton
import com.stripe.android.PaymentConfiguration
import com.stripe.android.view.CardInputWidget
import pe.idat.frontend.MainActivity
import pe.idat.frontend.R

class PaymentActivity : AppCompatActivity() {

    private lateinit var cardInputWidget: CardInputWidget
    private lateinit var payButton: MaterialButton

    private lateinit var userEmail: String
    private lateinit var membershipName: String
    private var membershipPrice: Float = 0F
    private lateinit var gymDirection: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        userEmail = MainActivity.prefs.getEmail()
        membershipName = MainActivity.prefs.getDescripcion()
        membershipPrice = MainActivity.prefs.getPrice().toFloat()
        gymDirection = MainActivity.prefs.getDirection()

        // Mostrar los datos en la interfaz de usuario
        val userEmailTextView: TextView = findViewById(R.id.textViewUserEmail)
        val membershipNameTextView: TextView = findViewById(R.id.textViewMembershipName)
        val membershipPriceTextView: TextView = findViewById(R.id.textViewMembershipPrice)
        val gymLocationTextView: TextView = findViewById(R.id.textViewGymDirection)

        userEmailTextView.text = "$userEmail"
        membershipNameTextView.text = "Membresia: $membershipName"
        membershipPriceTextView.text = "Precio: $membershipPrice"
        gymLocationTextView.text = "Ubicación: $gymDirection"

        PaymentConfiguration.init(applicationContext, "pk_test_51NcLL5AHgKa6SdywNHbQpS9qsOSJ3bQkaBEDn2wqmVl25XF1ryxLlFLtQOMn430tMcJcw2ll6s9BL1k9GL4lWIhw00k4PcwkOK")

        val selectedGymId = intent.getStringExtra("selectedGymId")
        cardInputWidget = findViewById(R.id.cardInputWidget)
        payButton = findViewById(R.id.payButton)

        payButton.setOnClickListener {
            val cardParams = cardInputWidget.cardParams

            if (cardParams != null) {
                // Simulamos un pago exitoso
                simulatePaymentSuccess()
                val intent = Intent(this, BottomNavigationActivity::class.java)
                startActivity(intent)
            } else {
                // Manejar error de entrada de tarjeta inválida
            }
        }
    }

    private fun simulatePaymentSuccess() {
        // Simulamos un pago exitoso aquí
        // En un escenario real, se interactuaría con el servidor de Stripe
        // para procesar el pago y obtener una confirmación exitosa.
    }
}