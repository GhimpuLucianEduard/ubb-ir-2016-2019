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

namespace ExamenSGBD
{
	public partial class Form1 : Form
	{
		private SqlDataAdapter _adapterTipuri;
		private SqlDataAdapter _adapterFlori;
		private DataSet _dataSet;
		private BindingSource _bsTipuri;
		private BindingSource _bsFlori;
		private SqlConnection conn;
		private SqlCommandBuilder _cb;

		public Form1()
		{
			InitializeComponent();
		}

		private void Form1_Load(object sender, EventArgs e)
		{
			conn = new SqlConnection("Data Source=GETH-MAINFRAME;Initial Catalog=ExamenSGBD;Integrated Security=True");
			_dataSet = new DataSet();
			_adapterFlori = new SqlDataAdapter("select * from Flori", conn);
			_adapterTipuri= new SqlDataAdapter("select * from TipuriFlori", conn);
			_adapterFlori.Fill(_dataSet, "Flori");
			_adapterTipuri.Fill(_dataSet, "TipuriFlori");
			_cb = new SqlCommandBuilder(_adapterFlori);
			var dr = new DataRelation("FK__Flori__TipuriFlori", _dataSet.Tables["TipuriFlori"].Columns["CodTip"], _dataSet.Tables["Flori"].Columns["Tip"]);
			_dataSet.Relations.Add(dr);
			_bsFlori = new BindingSource();
			_bsTipuri = new BindingSource();

			dataGridFlori.DataSource = _bsFlori;
			dataGridTipuri.DataSource = _bsTipuri;
			_bsTipuri.DataSource = _dataSet;
			_bsTipuri.DataMember = "TipuriFlori";

			_bsFlori.DataSource = _bsTipuri;
			_bsFlori.DataMember = "FK__Flori__TipuriFlori";
		}

		private void button1_Click(object sender, EventArgs e)
		{
			_adapterFlori.Update(_dataSet, "Flori");
		}
	}
}
