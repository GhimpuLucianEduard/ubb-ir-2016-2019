namespace ExamenSGBD
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
			this.dataGridTipuri = new System.Windows.Forms.DataGridView();
			this.dataGridFlori = new System.Windows.Forms.DataGridView();
			this.button1 = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.dataGridTipuri)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridFlori)).BeginInit();
			this.SuspendLayout();
			// 
			// dataGridTipuri
			// 
			this.dataGridTipuri.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridTipuri.Location = new System.Drawing.Point(13, 13);
			this.dataGridTipuri.Name = "dataGridTipuri";
			this.dataGridTipuri.RowTemplate.Height = 24;
			this.dataGridTipuri.Size = new System.Drawing.Size(373, 402);
			this.dataGridTipuri.TabIndex = 0;
			// 
			// dataGridFlori
			// 
			this.dataGridFlori.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridFlori.Location = new System.Drawing.Point(435, 13);
			this.dataGridFlori.Name = "dataGridFlori";
			this.dataGridFlori.RowTemplate.Height = 24;
			this.dataGridFlori.Size = new System.Drawing.Size(675, 402);
			this.dataGridFlori.TabIndex = 1;
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(13, 422);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(135, 38);
			this.button1.TabIndex = 2;
			this.button1.Text = "Refresh";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(1122, 472);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.dataGridFlori);
			this.Controls.Add(this.dataGridTipuri);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load);
			((System.ComponentModel.ISupportInitialize)(this.dataGridTipuri)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridFlori)).EndInit();
			this.ResumeLayout(false);

		}

		#endregion

		private System.Windows.Forms.DataGridView dataGridTipuri;
		private System.Windows.Forms.DataGridView dataGridFlori;
		private System.Windows.Forms.Button button1;
	}
}

