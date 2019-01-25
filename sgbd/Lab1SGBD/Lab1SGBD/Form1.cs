using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
namespace Lab1SGBD
{
    public partial class Form1 : Form
    { 
			SqlConnection conn = new SqlConnection("Data Source=GETH-MAINFRAME;Initial Catalog=UnitateMilitara;Integrated Security=True");
			SqlDataAdapter adapter = new SqlDataAdapter();
			DataSet dataSet = new DataSet();
			DataSet dataSet2 = new DataSet();


	    class Grad
	    {
		    public int idGrad { get; set; }
		    public string numeGrad { get; set; }

		    public Grad(int idGrad, string numeGrad)
		    {
			    this.idGrad = idGrad;
			    this.numeGrad = numeGrad;
		    }

		    public override string ToString()
		    {
			    return numeGrad;
		    }
	    }


        public Form1()
        {
            InitializeComponent();
	        conn.Open();
	        adapter.SelectCommand = new SqlCommand("select * from gradeMilitare", conn);
	        dataSet2.Clear();
	        adapter.Fill(dataSet2);
	        dataGridGrade.DataSource = dataSet2.Tables[0];
			var grade = dataSet2.Tables[0].AsEnumerable().Select(x => new Grad(x.Field<int>("id"), x.Field<string>("nume")))
				.ToList();
			comboBoxGrade.DataSource = grade;
	        adapter.SelectCommand = new SqlCommand("select * from soldati", conn);
	        dataSet.Clear();
	        adapter.Fill(dataSet);
	        dataGridSoldati.DataSource = dataSet.Tables[0];

			conn.Close();
		}

        

		private void button1_Click(object sender, EventArgs e)
		{	
			conn.Open();
			adapter.SelectCommand = new SqlCommand("select * from soldati",conn);
			dataSet.Clear();
			adapter.Fill(dataSet);
			dataGridSoldati.DataSource = dataSet.Tables[0];
			conn.Close();
			
		}

	

		private void button2_Click(object sender, EventArgs e)
		{

			if (dataGridSoldati.SelectedRows.Count > 0)
			{
				try
				{

					int row = dataGridSoldati.CurrentCell.RowIndex;
					var value = dataGridSoldati.Rows[row].Cells[0].Value;
					conn.Open();
					adapter.UpdateCommand =
						new SqlCommand(
							"update Soldati " +
							"set Cnp=@Cnp,Nume=@Nume,Prenume=@Prenume,Grad=@Grad,Inaltime=@Inaltime,Greutate=@Greutate,GrupaDeSange=@GrupaDeSange,DataNastere=@DataNastere " +
							"where id=@id");


					adapter.UpdateCommand.Parameters.AddWithValue("@id", SqlDbType.Int).Value = value;
					adapter.UpdateCommand.Parameters.AddWithValue("@Nume", SqlDbType.VarChar).Value = textBoxNume.Text;
					adapter.UpdateCommand.Parameters.AddWithValue("@Cnp", SqlDbType.Char).Value = textBoxCnp.Text;
					adapter.UpdateCommand.Parameters.AddWithValue("@Prenume", SqlDbType.VarChar).Value = textBoxPrenume.Text;
					var grad = (Grad)comboBoxGrade.SelectedItem;
					adapter.UpdateCommand.Parameters.AddWithValue("@Grad", SqlDbType.Int).Value = int.Parse(grad.idGrad.ToString());
					adapter.UpdateCommand.Parameters.AddWithValue("@Inaltime", SqlDbType.Int).Value = int.Parse(textBoxInaltime.Text);
					adapter.UpdateCommand.Parameters.AddWithValue("@Greutate", SqlDbType.Float).Value = float.Parse(textBoxGreutate.Text);
					adapter.UpdateCommand.Parameters.AddWithValue("@GrupaDeSange", SqlDbType.Int).Value = int.Parse(textBoxSange.Text);
					adapter.UpdateCommand.Parameters.AddWithValue("@DataNastere", SqlDbType.Date).Value = DateTime.Parse(textBoxData.Text);

					adapter.UpdateCommand.Connection = conn;
					adapter.UpdateCommand.ExecuteNonQuery();


				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.ToString(), "Warning",
						MessageBoxButtons.OK, MessageBoxIcon.Error);
					throw;
				}
				finally
				{
					conn.Close();
				}
			}
			else
			{
				try
				{
					conn.Open();
					adapter.InsertCommand =
						new SqlCommand(
							"insert into Soldati(Cnp,Nume,Prenume,Grad,Inaltime,Greutate,GrupaDeSange,DataNastere) values (@Cnp,@Nume,@Prenume,@Grad,@Inaltime,@Greutate,@GrupaDeSange,@DataNastere)");

					adapter.InsertCommand.Parameters.AddWithValue("@Nume", SqlDbType.VarChar).Value = textBoxNume.Text;
					adapter.InsertCommand.Parameters.AddWithValue("@Cnp", SqlDbType.Char).Value = textBoxCnp.Text;
					adapter.InsertCommand.Parameters.AddWithValue("@Prenume", SqlDbType.VarChar).Value = textBoxPrenume.Text;
					var grad = (Grad)comboBoxGrade.SelectedItem;
					adapter.InsertCommand.Parameters.AddWithValue("@Grad", SqlDbType.Int).Value = int.Parse(grad.idGrad.ToString());
					adapter.InsertCommand.Parameters.AddWithValue("@Inaltime", SqlDbType.Int).Value = int.Parse(textBoxInaltime.Text);
					adapter.InsertCommand.Parameters.AddWithValue("@Greutate", SqlDbType.Float).Value = float.Parse(textBoxGreutate.Text);
					adapter.InsertCommand.Parameters.AddWithValue("@GrupaDeSange", SqlDbType.Int).Value = int.Parse(textBoxSange.Text);
					adapter.InsertCommand.Parameters.AddWithValue("@DataNastere", SqlDbType.Date).Value = DateTime.Parse(textBoxData.Text);

					adapter.InsertCommand.Connection = conn;
					adapter.InsertCommand.ExecuteNonQuery();


				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.ToString(), "Warning",
						MessageBoxButtons.OK, MessageBoxIcon.Error);
					//throw;
				}
				finally
				{
					conn.Close();
				}
			}


			
		}

		

		private void buttonGrade_Click(object sender, EventArgs e)
		{
			conn.Open();
			adapter.SelectCommand = new SqlCommand("select * from gradeMilitare", conn);
			dataSet2.Clear();
			adapter.Fill(dataSet2);
			dataGridGrade.DataSource = dataSet2.Tables[0];
			//var empList = ds.Tables[0].AsEnumerable().Select(dataRow => new Employee { Name = dataRow.Field<string>("Name") }).ToList();

			var grade = dataSet2.Tables[0].AsEnumerable().Select(x => new Grad(x.Field<int>("id"), x.Field<string>("nume")))
				.ToList();
			comboBoxGrade.DataSource = grade;


			conn.Close();
		}

		

		private void dataGridGrade_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
		{
			conn.Open();
			int row = dataGridGrade.CurrentCell.RowIndex;
			var value = dataGridGrade.Rows[row].Cells[0].Value;
			adapter.SelectCommand = new SqlCommand("select * from soldati where grad = @value ", conn);
			adapter.SelectCommand.Parameters.AddWithValue("@value", SqlDbType.Int).Value = int.Parse(value.ToString());
			dataSet.Clear();
			adapter.Fill(dataSet);
			dataGridSoldati.DataSource = dataSet.Tables[0];
			conn.Close();
		}

		private void dataGridSoldati_CellMouseClick(object sender, DataGridViewCellMouseEventArgs e)
		{
			int row = dataGridSoldati.CurrentCell.RowIndex;
			var value = dataGridSoldati.Rows[row];
			textBoxCnp.Text = value.Cells[1].Value.ToString();
			textBoxNume.Text = value.Cells[2].Value.ToString();
			textBoxPrenume.Text = value.Cells[3].Value.ToString();
			textBoxGreutate.Text = value.Cells[5].Value.ToString();
			textBoxInaltime.Text = value.Cells[6].Value.ToString();
			textBoxSange.Text = value.Cells[7].Value.ToString();
			textBoxData.Text = value.Cells[8].Value.ToString();

			for (int i = 0; i < comboBoxGrade.Items.Count; i++)
			{
				Grad grad = comboBoxGrade.Items[i] as Grad;
				if (grad.idGrad == int.Parse(value.Cells[4].Value.ToString()))
				{
					comboBoxGrade.SelectedItem = comboBoxGrade.Items[i];
				}
			}
		}

		private void buttonStergeSoldat_Click(object sender, EventArgs e)
		{
			if (dataGridSoldati.SelectedRows.Count > 0)
			{
				try
				{

					int row = dataGridSoldati.CurrentCell.RowIndex;
					var value = dataGridSoldati.Rows[row].Cells[0].Value;
					conn.Open();
					adapter.DeleteCommand =
						new SqlCommand(
							"delete " +
							"from Soldati " +
							"where id=@id");


					adapter.DeleteCommand.Parameters.AddWithValue("@id", SqlDbType.Int).Value = value;
					adapter.DeleteCommand.Connection = conn;
					adapter.DeleteCommand.ExecuteNonQuery();


				}
				catch (Exception exception)
				{
					MessageBox.Show(exception.ToString(), "Warning",
						MessageBoxButtons.OK, MessageBoxIcon.Error);
					throw;
				}
				finally
				{
					conn.Close();
				}
			}
			else
			{
				MessageBox.Show("Selecteaza un rand din tabela soldati!", "Warning",
					MessageBoxButtons.OK, MessageBoxIcon.Error);
			}
		}
	}

   
}
