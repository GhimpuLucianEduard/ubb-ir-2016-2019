namespace Restaurant
{
	partial class Form1
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.dataGridComanda = new System.Windows.Forms.DataGridView();
			this.dataGridIteme = new System.Windows.Forms.DataGridView();
			((System.ComponentModel.ISupportInitialize)(this.dataGridComanda)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridIteme)).BeginInit();
			this.SuspendLayout();
			// 
			// dataGridComanda
			// 
			this.dataGridComanda.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridComanda.Location = new System.Drawing.Point(13, 13);
			this.dataGridComanda.Name = "dataGridComanda";
			this.dataGridComanda.RowTemplate.Height = 24;
			this.dataGridComanda.Size = new System.Drawing.Size(661, 514);
			this.dataGridComanda.TabIndex = 0;
			// 
			// dataGridIteme
			// 
			this.dataGridIteme.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridIteme.Location = new System.Drawing.Point(701, 13);
			this.dataGridIteme.Name = "dataGridIteme";
			this.dataGridIteme.RowTemplate.Height = 24;
			this.dataGridIteme.Size = new System.Drawing.Size(598, 514);
			this.dataGridIteme.TabIndex = 1;
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(1311, 607);
			this.Controls.Add(this.dataGridIteme);
			this.Controls.Add(this.dataGridComanda);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load_1);
			((System.ComponentModel.ISupportInitialize)(this.dataGridComanda)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridIteme)).EndInit();
			this.ResumeLayout(false);

		}

		#endregion

		private System.Windows.Forms.DataGridView dataGridComanda;
		private System.Windows.Forms.DataGridView dataGridIteme;
	}
}

