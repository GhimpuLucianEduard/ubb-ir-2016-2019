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

namespace semianr2sgbd
{
	public partial class Form1 : Form
	{
		private SqlDataAdapter adapterSoldati;
		private SqlDataAdapter adapterGrade;
		private DataSet dataSet;
		private SqlCommandBuilder cb; 
		private BindingSource bsSoldati;
		private BindingSource bsGrade;
		private SqlConnection conn;

		public Form1()
		{
			InitializeComponent();
		}

		private void Form1_Load(object sender, EventArgs e)
		{
			conn = new SqlConnection("Data Source=GETH-MAINFRAME;Initial Catalog=UnitateMilitara;Integrated Security=True");
			dataSet = new DataSet();
			adapterSoldati = new SqlDataAdapter("select * from Soldati",conn);
			adapterGrade = new SqlDataAdapter("select * from GradeMilitare",conn);
			cb = new SqlCommandBuilder(adapterSoldati);
			adapterSoldati.Fill(dataSet, "Soldati");
			adapterGrade.Fill(dataSet, "GradeMilitare");

			DataRelation dr = new DataRelation("FK__Soldati__Grad__0D7A0286", dataSet.Tables["GradeMilitare"].Columns["id"],dataSet.Tables["Soldati"].Columns["Grad"]);
			dataSet.Relations.Add(dr);
			bsSoldati = new BindingSource();
			bsGrade = new BindingSource();

			dataGridSoldati.DataSource = bsSoldati;
			dataGridGrade.DataSource = bsGrade;
			bsGrade.DataSource = dataSet;
			bsGrade.DataMember = "GradeMilitare";

			bsSoldati.DataSource = bsGrade;
			bsSoldati.DataMember = "FK__Soldati__Grad__0D7A0286";

		}

		private void buttonRefresh_Click(object sender, EventArgs e)
		{
			adapterSoldati.Update(dataSet, "Soldati");
		}
	}
}
