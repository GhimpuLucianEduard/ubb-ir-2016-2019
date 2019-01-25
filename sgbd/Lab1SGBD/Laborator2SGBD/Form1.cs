using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Laborator2SGBD
{
	public partial class Form1 : Form
	{
		private SqlConnection _conn;
		private string _child;
		private string _parent;
		SqlDataAdapter _adapterParent = new SqlDataAdapter();
		SqlDataAdapter _adapterChild = new SqlDataAdapter();
		DataSet _dataSet = new DataSet();
		private BindingSource bsParent;
		private BindingSource bsChild;


		public Form1()
		{
			InitializeComponent();

		}

		private void button1_Click(object sender, EventArgs e)
		{
			var cs = ConfigurationManager.ConnectionStrings["sgbd"].ConnectionString;
			_conn = new SqlConnection(cs);
			_child = ConfigurationManager.AppSettings["ChildTableName"];
			_parent = ConfigurationManager.AppSettings["ParentTableName"];
			GetData();
			BindTables();
			AddFields();
		}

		private void AddFields()
		{
			var nrFields = ConfigurationManager.AppSettings["ChildTableNumberOfCol"];
			var colNames = ConfigurationManager.AppSettings["ChildColNames"];
			var list = colNames.Split(',');
			var y = 0;
			foreach (var s in list)
			{	
				var textBox = new TextBox();
				textBox.Location = new System.Drawing.Point(0, y);
				textBox.Size = new System.Drawing.Size(150,25);

				textBox.DataBindings.Add(new Binding("Text", bsChild, s, true));

				var label = new Label();
				label.Text = s;
				label.Location = new System.Drawing.Point(160, y);
				label.Size = new System.Drawing.Size(100, 25);

				textBox.Name = s;
				ContainerPanel.Controls.Add(label);
				ContainerPanel.Controls.Add(textBox);
				y += 30;

			}
			this.Refresh();
		}

		private void BindTables()
		{
			var relName = ConfigurationManager.AppSettings["RelName"];
			var childColName = ConfigurationManager.AppSettings["ColRelChild"];

			var dr = new DataRelation(relName, _dataSet.Tables[_parent].Columns["id"], _dataSet.Tables[_child].Columns[childColName]);

			_dataSet.Relations.Add(dr);
			bsChild = new BindingSource();
			bsParent = new BindingSource();

			dataGridViewChild.DataSource = bsChild;
			dataGridViewParent.DataSource = bsParent;

			bsParent.DataSource = _dataSet;
			bsParent.DataMember = _parent;

			bsChild.DataSource = bsParent;
			bsChild.DataMember = relName;
		}

		private void GetData()
		{
			_conn.Open();
			_dataSet.Clear();
			var selectChild = ConfigurationManager.AppSettings["SelectChild"];
			var selectParent = ConfigurationManager.AppSettings["SelectParent"];

			_adapterChild.SelectCommand = new SqlCommand(selectChild, _conn);
			_adapterParent.SelectCommand = new SqlCommand(selectParent, _conn);
			_dataSet.Clear();
			_adapterParent.Fill(_dataSet, _parent);
			_adapterChild.Fill(_dataSet, _child);

			_conn.Close();
		}

		private void button2_Click(object sender, EventArgs e)
		{
			_conn.Open();
			var insert = ConfigurationManager.AppSettings["Insert"];
			var colNames = ConfigurationManager.AppSettings["ChildColNames"];
			var list = colNames.Split(',');

			var cmd = new SqlCommand(insert,_conn);

			foreach (var column in list)
			{
				var textBox = (TextBox)ContainerPanel.Controls[column];
				cmd.Parameters.AddWithValue("@" + column,
					textBox.Text);
			}

			cmd.ExecuteNonQuery();
			_conn.Close();
			GetData();
		}

		private void button3_Click(object sender, EventArgs e)
		{
			if (dataGridViewChild.SelectedRows.Count > 0)
			{
				_conn.Open();
				var delete = ConfigurationManager.AppSettings["Delete"];

				var cmd = new SqlCommand(delete, _conn);

				var row = dataGridViewChild.CurrentCell.RowIndex;
				var value = dataGridViewChild.Rows[row].Cells[0].Value;

				cmd.Parameters.AddWithValue("@id",value);
				cmd.ExecuteNonQuery();
				_conn.Close();
				GetData();
			}
			else
			{
				MessageBox.Show("Selecteaza un rand din tabela copil!", "Warning",
					MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}

		private void button4_Click(object sender, EventArgs e)
		{
			if (dataGridViewChild.SelectedRows.Count > 0)
			{
				_conn.Open();
				var insert = ConfigurationManager.AppSettings["Update"];
				var colNames = ConfigurationManager.AppSettings["ChildColNames"];
				var list = colNames.Split(',');

				var cmd = new SqlCommand(insert, _conn);

				var row = dataGridViewChild.CurrentCell.RowIndex;
				var value = dataGridViewChild.Rows[row].Cells[0].Value;

				cmd.Parameters.AddWithValue("@id", value);

				foreach (var column in list)
				{
					var textBox = (TextBox)ContainerPanel.Controls[column];
					cmd.Parameters.AddWithValue("@" + column,
						textBox.Text);
				}

				cmd.ExecuteNonQuery();
				_conn.Close();
				GetData();
			}
			else
			{
				MessageBox.Show("Selecteaza un rand din tabela copil!", "Warning",
					MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}
	}
}
