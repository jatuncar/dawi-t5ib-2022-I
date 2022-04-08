package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Categoria;
import model.Producto;
import model.Proveedor;
import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCódigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 390);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCódigo = new JTextField();
		txtCódigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCódigo);
		txtCódigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnNewButton_1.setBounds(324, 63, 89, 23);
		contentPane.add(btnNewButton_1);

		llenaCombo();
	}

	void llenaCombo() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		List<Categoria> lstCategorias = em.createQuery("select a from Categoria a", Categoria.class).getResultList();
		cboCategorias.addItem("Seleccione");
		for (Categoria c : lstCategorias) {
			cboCategorias.addItem(c.getIdcategoria() + "-" + c.getDescripcion());
		}

		List<Proveedor> proveedores = em.createQuery("select p from Proveedor p", Proveedor.class).getResultList();
		cboProveedores.addItem("Seleccione");
		for (Proveedor p : proveedores) {
			cboProveedores.addItem(p.getNombre_rs());
		}
		em.close();
	}

	void listado() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		TypedQuery<Producto> consulta = em.createQuery("select pro from Producto pro", Producto.class);
		List<Producto> listadoProductos = consulta.getResultList();

		txtSalida.setText("");
		for (Producto p : listadoProductos) {
			txtSalida.append("************************************************" + "\n");
			txtSalida.append("Id Producto : " + p.getCodigo() + "\n");
			txtSalida.append("Descripcicon: " + p.getDescripcion() + "\n");
			txtSalida.append("Stock    : " + Integer.toString(p.getStock()) + "\n");
			txtSalida.append("Precio   : " + Double.toString(p.getPrecio()) + "\n");
			txtSalida.append("Categoria: " + p.getIdcategoria() + "/" + p.getCategoria().getDescripcion() + "\n");
			txtSalida.append("Estado   : " + Integer.toString(p.getEstado()) + "\n");
			txtSalida.append("Id Proveedor: " + p.getIdprovedor() + " " + p.getProveedor().getNombre_rs() + "\n");
		}

		em.close();
	}

	void buscar() {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();

		Producto p = em.find(Producto.class, txtCódigo.getText());

		// Salida
		txtDescripcion.setText(p.getDescripcion());
		//cboCategorias.setSelectedItem(p.getCategoria().getDescripcion());
		cboCategorias.setSelectedIndex(p.getIdcategoria());
		txtPrecio.setText(Double.toString(p.getPrecio()));
		txtStock.setText(Integer.toString(p.getStock()));
		cboProveedores.setSelectedItem(p.getProveedor().getNombre_rs());
		em.close();
	}

	void registrar() {
		// entradas
		String codigo = txtCódigo.getText();
		String descripcion = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int estado = 1; // valor x default -> Activo
		int proveedor = cboProveedores.getSelectedIndex();

		// proceso
		Producto p = new Producto();
		p.setCodigo(codigo);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado);
		p.setIdprovedor(proveedor);

		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		JOptionPane.showMessageDialog(this, "Producto registrado");
	}
}
