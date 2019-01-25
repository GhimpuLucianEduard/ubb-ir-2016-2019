namespace Lab1SGBD
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
			this.dataGridSoldati = new System.Windows.Forms.DataGridView();
			this.button1 = new System.Windows.Forms.Button();
			this.label2 = new System.Windows.Forms.Label();
			this.textBoxCnp = new System.Windows.Forms.TextBox();
			this.buttonAdd = new System.Windows.Forms.Button();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.label5 = new System.Windows.Forms.Label();
			this.label6 = new System.Windows.Forms.Label();
			this.label7 = new System.Windows.Forms.Label();
			this.label8 = new System.Windows.Forms.Label();
			this.label9 = new System.Windows.Forms.Label();
			this.textBoxNume = new System.Windows.Forms.TextBox();
			this.textBoxPrenume = new System.Windows.Forms.TextBox();
			this.textBoxGreutate = new System.Windows.Forms.TextBox();
			this.textBoxInaltime = new System.Windows.Forms.TextBox();
			this.textBoxSange = new System.Windows.Forms.TextBox();
			this.textBoxData = new System.Windows.Forms.TextBox();
			this.dataGridGrade = new System.Windows.Forms.DataGridView();
			this.buttonGrade = new System.Windows.Forms.Button();
			this.comboBoxGrade = new System.Windows.Forms.ComboBox();
			this.buttonStergeSoldat = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.dataGridSoldati)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridGrade)).BeginInit();
			this.SuspendLayout();
			// 
			// dataGridSoldati
			// 
			this.dataGridSoldati.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridSoldati.Location = new System.Drawing.Point(13, 13);
			this.dataGridSoldati.Name = "dataGridSoldati";
			this.dataGridSoldati.RowTemplate.Height = 24;
			this.dataGridSoldati.Size = new System.Drawing.Size(834, 343);
			this.dataGridSoldati.TabIndex = 0;
			this.dataGridSoldati.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.dataGridSoldati_CellMouseClick);
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(253, 375);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(244, 31);
			this.button1.TabIndex = 1;
			this.button1.Text = "Refresh Soldati";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(876, 71);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(36, 17);
			this.label2.TabIndex = 3;
			this.label2.Text = "CNP";
			// 
			// textBoxCnp
			// 
			this.textBoxCnp.Location = new System.Drawing.Point(995, 71);
			this.textBoxCnp.Name = "textBoxCnp";
			this.textBoxCnp.Size = new System.Drawing.Size(196, 22);
			this.textBoxCnp.TabIndex = 5;
			// 
			// buttonAdd
			// 
			this.buttonAdd.Location = new System.Drawing.Point(882, 375);
			this.buttonAdd.Name = "buttonAdd";
			this.buttonAdd.Size = new System.Drawing.Size(309, 31);
			this.buttonAdd.TabIndex = 6;
			this.buttonAdd.Text = "Adauga/Update";
			this.buttonAdd.UseVisualStyleBackColor = true;
			this.buttonAdd.Click += new System.EventHandler(this.button2_Click);
			// 
			// label3
			// 
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(879, 102);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(45, 17);
			this.label3.TabIndex = 7;
			this.label3.Text = "Nume";
			// 
			// label4
			// 
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(879, 138);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(65, 17);
			this.label4.TabIndex = 8;
			this.label4.Text = "Prenume";
			// 
			// label5
			// 
			this.label5.AutoSize = true;
			this.label5.Location = new System.Drawing.Point(879, 168);
			this.label5.Name = "label5";
			this.label5.Size = new System.Drawing.Size(40, 17);
			this.label5.TabIndex = 9;
			this.label5.Text = "Grad";
			// 
			// label6
			// 
			this.label6.AutoSize = true;
			this.label6.Location = new System.Drawing.Point(879, 201);
			this.label6.Name = "label6";
			this.label6.Size = new System.Drawing.Size(64, 17);
			this.label6.TabIndex = 10;
			this.label6.Text = "Greutate";
			// 
			// label7
			// 
			this.label7.AutoSize = true;
			this.label7.Location = new System.Drawing.Point(879, 235);
			this.label7.Name = "label7";
			this.label7.Size = new System.Drawing.Size(56, 17);
			this.label7.TabIndex = 11;
			this.label7.Text = "Inaltime";
			// 
			// label8
			// 
			this.label8.AutoSize = true;
			this.label8.Location = new System.Drawing.Point(879, 269);
			this.label8.Name = "label8";
			this.label8.Size = new System.Drawing.Size(111, 17);
			this.label8.TabIndex = 12;
			this.label8.Text = "Grupa de sange";
			// 
			// label9
			// 
			this.label9.AutoSize = true;
			this.label9.Location = new System.Drawing.Point(879, 300);
			this.label9.Name = "label9";
			this.label9.Size = new System.Drawing.Size(90, 17);
			this.label9.TabIndex = 13;
			this.label9.Text = "Data Nasterii";
			// 
			// textBoxNume
			// 
			this.textBoxNume.Location = new System.Drawing.Point(995, 102);
			this.textBoxNume.Name = "textBoxNume";
			this.textBoxNume.Size = new System.Drawing.Size(196, 22);
			this.textBoxNume.TabIndex = 14;
			// 
			// textBoxPrenume
			// 
			this.textBoxPrenume.Location = new System.Drawing.Point(995, 138);
			this.textBoxPrenume.Name = "textBoxPrenume";
			this.textBoxPrenume.Size = new System.Drawing.Size(196, 22);
			this.textBoxPrenume.TabIndex = 15;
			// 
			// textBoxGreutate
			// 
			this.textBoxGreutate.Location = new System.Drawing.Point(995, 201);
			this.textBoxGreutate.Name = "textBoxGreutate";
			this.textBoxGreutate.Size = new System.Drawing.Size(196, 22);
			this.textBoxGreutate.TabIndex = 17;
			// 
			// textBoxInaltime
			// 
			this.textBoxInaltime.Location = new System.Drawing.Point(995, 235);
			this.textBoxInaltime.Name = "textBoxInaltime";
			this.textBoxInaltime.Size = new System.Drawing.Size(196, 22);
			this.textBoxInaltime.TabIndex = 18;
			// 
			// textBoxSange
			// 
			this.textBoxSange.Location = new System.Drawing.Point(995, 269);
			this.textBoxSange.Name = "textBoxSange";
			this.textBoxSange.Size = new System.Drawing.Size(196, 22);
			this.textBoxSange.TabIndex = 19;
			// 
			// textBoxData
			// 
			this.textBoxData.Location = new System.Drawing.Point(995, 297);
			this.textBoxData.Name = "textBoxData";
			this.textBoxData.Size = new System.Drawing.Size(196, 22);
			this.textBoxData.TabIndex = 20;
			// 
			// dataGridGrade
			// 
			this.dataGridGrade.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridGrade.Location = new System.Drawing.Point(1230, 13);
			this.dataGridGrade.Name = "dataGridGrade";
			this.dataGridGrade.RowTemplate.Height = 24;
			this.dataGridGrade.Size = new System.Drawing.Size(344, 343);
			this.dataGridGrade.TabIndex = 21;
			this.dataGridGrade.CellMouseClick += new System.Windows.Forms.DataGridViewCellMouseEventHandler(this.dataGridGrade_CellMouseClick);
			// 
			// buttonGrade
			// 
			this.buttonGrade.Location = new System.Drawing.Point(1259, 375);
			this.buttonGrade.Name = "buttonGrade";
			this.buttonGrade.Size = new System.Drawing.Size(278, 31);
			this.buttonGrade.TabIndex = 22;
			this.buttonGrade.Text = "Refresh Grade";
			this.buttonGrade.UseVisualStyleBackColor = true;
			this.buttonGrade.Click += new System.EventHandler(this.buttonGrade_Click);
			// 
			// comboBoxGrade
			// 
			this.comboBoxGrade.FormattingEnabled = true;
			this.comboBoxGrade.Location = new System.Drawing.Point(995, 168);
			this.comboBoxGrade.Name = "comboBoxGrade";
			this.comboBoxGrade.Size = new System.Drawing.Size(196, 24);
			this.comboBoxGrade.TabIndex = 23;
			// 
			// buttonStergeSoldat
			// 
			this.buttonStergeSoldat.Location = new System.Drawing.Point(528, 375);
			this.buttonStergeSoldat.Name = "buttonStergeSoldat";
			this.buttonStergeSoldat.Size = new System.Drawing.Size(275, 31);
			this.buttonStergeSoldat.TabIndex = 24;
			this.buttonStergeSoldat.Text = "Sterge Soldat";
			this.buttonStergeSoldat.UseVisualStyleBackColor = true;
			this.buttonStergeSoldat.Click += new System.EventHandler(this.buttonStergeSoldat_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(1586, 436);
			this.Controls.Add(this.buttonStergeSoldat);
			this.Controls.Add(this.comboBoxGrade);
			this.Controls.Add(this.buttonGrade);
			this.Controls.Add(this.dataGridGrade);
			this.Controls.Add(this.textBoxData);
			this.Controls.Add(this.textBoxSange);
			this.Controls.Add(this.textBoxInaltime);
			this.Controls.Add(this.textBoxGreutate);
			this.Controls.Add(this.textBoxPrenume);
			this.Controls.Add(this.textBoxNume);
			this.Controls.Add(this.label9);
			this.Controls.Add(this.label8);
			this.Controls.Add(this.label7);
			this.Controls.Add(this.label6);
			this.Controls.Add(this.label5);
			this.Controls.Add(this.label4);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.buttonAdd);
			this.Controls.Add(this.textBoxCnp);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.dataGridSoldati);
			this.Name = "Form1";
			this.Text = "Form1";
			((System.ComponentModel.ISupportInitialize)(this.dataGridSoldati)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridGrade)).EndInit();
			this.ResumeLayout(false);
			this.PerformLayout();

        }

		#endregion

		private System.Windows.Forms.DataGridView dataGridSoldati;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox textBoxCnp;
		private System.Windows.Forms.Button buttonAdd;
		private System.Windows.Forms.Label label3;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.Label label5;
		private System.Windows.Forms.Label label6;
		private System.Windows.Forms.Label label7;
		private System.Windows.Forms.Label label8;
		private System.Windows.Forms.Label label9;
		private System.Windows.Forms.TextBox textBoxNume;
		private System.Windows.Forms.TextBox textBoxPrenume;
		private System.Windows.Forms.TextBox textBoxGreutate;
		private System.Windows.Forms.TextBox textBoxInaltime;
		private System.Windows.Forms.TextBox textBoxSange;
		private System.Windows.Forms.TextBox textBoxData;
		private System.Windows.Forms.DataGridView dataGridGrade;
		private System.Windows.Forms.Button buttonGrade;
		private System.Windows.Forms.ComboBox comboBoxGrade;
		private System.Windows.Forms.Button buttonStergeSoldat;
	}
}

