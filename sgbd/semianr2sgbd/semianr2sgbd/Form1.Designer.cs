namespace semianr2sgbd
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
			this.labelSoldati = new System.Windows.Forms.Label();
			this.labelGrade = new System.Windows.Forms.Label();
			this.dataGridSoldati = new System.Windows.Forms.DataGridView();
			this.dataGridGrade = new System.Windows.Forms.DataGridView();
			this.buttonRefresh = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.dataGridSoldati)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridGrade)).BeginInit();
			this.SuspendLayout();
			// 
			// labelSoldati
			// 
			this.labelSoldati.AutoSize = true;
			this.labelSoldati.Location = new System.Drawing.Point(13, 13);
			this.labelSoldati.Name = "labelSoldati";
			this.labelSoldati.Size = new System.Drawing.Size(46, 17);
			this.labelSoldati.TabIndex = 0;
			this.labelSoldati.Text = "label1";
			// 
			// labelGrade
			// 
			this.labelGrade.AutoSize = true;
			this.labelGrade.Location = new System.Drawing.Point(94, 13);
			this.labelGrade.Name = "labelGrade";
			this.labelGrade.Size = new System.Drawing.Size(46, 17);
			this.labelGrade.TabIndex = 1;
			this.labelGrade.Text = "label2";
			// 
			// dataGridSoldati
			// 
			this.dataGridSoldati.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridSoldati.Location = new System.Drawing.Point(16, 44);
			this.dataGridSoldati.Name = "dataGridSoldati";
			this.dataGridSoldati.RowTemplate.Height = 24;
			this.dataGridSoldati.Size = new System.Drawing.Size(462, 225);
			this.dataGridSoldati.TabIndex = 2;
			// 
			// dataGridGrade
			// 
			this.dataGridGrade.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridGrade.Location = new System.Drawing.Point(516, 44);
			this.dataGridGrade.Name = "dataGridGrade";
			this.dataGridGrade.RowTemplate.Height = 24;
			this.dataGridGrade.Size = new System.Drawing.Size(233, 185);
			this.dataGridGrade.TabIndex = 3;
			// 
			// buttonRefresh
			// 
			this.buttonRefresh.Location = new System.Drawing.Point(513, 235);
			this.buttonRefresh.Name = "buttonRefresh";
			this.buttonRefresh.Size = new System.Drawing.Size(123, 34);
			this.buttonRefresh.TabIndex = 4;
			this.buttonRefresh.Text = "Refresh";
			this.buttonRefresh.UseVisualStyleBackColor = true;
			this.buttonRefresh.Click += new System.EventHandler(this.buttonRefresh_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(850, 281);
			this.Controls.Add(this.buttonRefresh);
			this.Controls.Add(this.dataGridGrade);
			this.Controls.Add(this.dataGridSoldati);
			this.Controls.Add(this.labelGrade);
			this.Controls.Add(this.labelSoldati);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load);
			((System.ComponentModel.ISupportInitialize)(this.dataGridSoldati)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridGrade)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label labelSoldati;
		private System.Windows.Forms.Label labelGrade;
		private System.Windows.Forms.DataGridView dataGridSoldati;
		private System.Windows.Forms.DataGridView dataGridGrade;
		private System.Windows.Forms.Button buttonRefresh;
	}
}

