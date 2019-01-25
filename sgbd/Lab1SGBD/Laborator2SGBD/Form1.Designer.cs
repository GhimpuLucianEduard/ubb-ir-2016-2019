namespace Laborator2SGBD
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
			this.ContainerPanel = new System.Windows.Forms.Panel();
			this.dataGridViewChild = new System.Windows.Forms.DataGridView();
			this.dataGridViewParent = new System.Windows.Forms.DataGridView();
			this.button1 = new System.Windows.Forms.Button();
			this.button2 = new System.Windows.Forms.Button();
			this.button3 = new System.Windows.Forms.Button();
			this.button4 = new System.Windows.Forms.Button();
			((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).BeginInit();
			this.SuspendLayout();
			// 
			// ContainerPanel
			// 
			this.ContainerPanel.BackColor = System.Drawing.SystemColors.Control;
			this.ContainerPanel.Location = new System.Drawing.Point(13, 13);
			this.ContainerPanel.Name = "ContainerPanel";
			this.ContainerPanel.Size = new System.Drawing.Size(293, 413);
			this.ContainerPanel.TabIndex = 0;
			// 
			// dataGridViewChild
			// 
			this.dataGridViewChild.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridViewChild.Location = new System.Drawing.Point(328, 13);
			this.dataGridViewChild.Name = "dataGridViewChild";
			this.dataGridViewChild.RowTemplate.Height = 24;
			this.dataGridViewChild.Size = new System.Drawing.Size(541, 413);
			this.dataGridViewChild.TabIndex = 1;
			// 
			// dataGridViewParent
			// 
			this.dataGridViewParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridViewParent.Location = new System.Drawing.Point(889, 12);
			this.dataGridViewParent.Name = "dataGridViewParent";
			this.dataGridViewParent.RowTemplate.Height = 24;
			this.dataGridViewParent.Size = new System.Drawing.Size(418, 413);
			this.dataGridViewParent.TabIndex = 2;
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(13, 451);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(114, 39);
			this.button1.TabIndex = 3;
			this.button1.Text = "Connect";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// button2
			// 
			this.button2.Location = new System.Drawing.Point(174, 451);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(132, 39);
			this.button2.TabIndex = 4;
			this.button2.Text = "Add";
			this.button2.UseVisualStyleBackColor = true;
			this.button2.Click += new System.EventHandler(this.button2_Click);
			// 
			// button3
			// 
			this.button3.Location = new System.Drawing.Point(328, 451);
			this.button3.Name = "button3";
			this.button3.Size = new System.Drawing.Size(121, 39);
			this.button3.TabIndex = 5;
			this.button3.Text = "Delete";
			this.button3.UseVisualStyleBackColor = true;
			this.button3.Click += new System.EventHandler(this.button3_Click);
			// 
			// button4
			// 
			this.button4.Location = new System.Drawing.Point(483, 451);
			this.button4.Name = "button4";
			this.button4.Size = new System.Drawing.Size(143, 39);
			this.button4.TabIndex = 6;
			this.button4.Text = "Update";
			this.button4.UseVisualStyleBackColor = true;
			this.button4.Click += new System.EventHandler(this.button4_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(1312, 520);
			this.Controls.Add(this.button4);
			this.Controls.Add(this.button3);
			this.Controls.Add(this.button2);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.dataGridViewParent);
			this.Controls.Add(this.dataGridViewChild);
			this.Controls.Add(this.ContainerPanel);
			this.Name = "Form1";
			this.Text = "Form1";
			((System.ComponentModel.ISupportInitialize)(this.dataGridViewChild)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).EndInit();
			this.ResumeLayout(false);

		}

		#endregion

		private System.Windows.Forms.Panel ContainerPanel;
		private System.Windows.Forms.DataGridView dataGridViewChild;
		private System.Windows.Forms.DataGridView dataGridViewParent;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Button button2;
		private System.Windows.Forms.Button button3;
		private System.Windows.Forms.Button button4;
	}
}

