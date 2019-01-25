using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Restaurant
{
	public partial class Form1 : Form
	{
		private SqlDataAdapter adapterComenzi;
		private SqlDataAdapter adapterIteme;
		private DataSet dataSet;
		//private SqlCommandBuilder cb;
		private BindingSource bsComenzi;
		private BindingSource bsIteme;
		private SqlConnection conn;

		public Form1()
		{
			InitializeComponent();
		}


		private void Form1_Load_1(object sender, EventArgs e)
		{
			conn = new SqlConnection("Data Source=GETH-MAINFRAME;Initial Catalog=Restaurant;Integrated Security=True");
			dataSet = new DataSet();
			adapterComenzi = new SqlDataAdapter("select * from Comanda", conn);
			adapterIteme = new SqlDataAdapter("select * from ComandaItem", conn);
			//cb = new SqlCommandBuilder(adapterSoldati);
			adapterComenzi.Fill(dataSet, "Comanda");
			adapterIteme.Fill(dataSet, "ComandaItem");

			DataRelation dr = new DataRelation("FK__ComandaItem__Comanda", dataSet.Tables["Comanda"].Columns["codComanda"], dataSet.Tables["ComandaItem"].Columns["codComanda"]);
			dataSet.Relations.Add(dr);
			bsIteme = new BindingSource();
			bsComenzi = new BindingSource();

			dataGridComanda.DataSource = bsComenzi;
			dataGridIteme.DataSource = bsIteme;
			bsComenzi.DataSource = dataSet;
			bsComenzi.DataMember = "Comanda";

			bsIteme.DataSource = bsComenzi;
			bsIteme.DataMember = "FK__ComandaItem__Comanda";
		}
	}
}
