﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Domain.Models
{
    using System;
    using System.Data.Entity;
    using System.Data.Entity.Infrastructure;
    
    public partial class ExamenMppEntities : DbContext
    {
        public ExamenMppEntities()
            : base("name=ExamenMppEntities")
        {
        }
    
        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            throw new UnintentionalCodeFirstException();
        }
    
        public virtual DbSet<Joc> Jocs { get; set; }
        public virtual DbSet<Jucator> Jucators { get; set; }
        public virtual DbSet<Mutare> Mutares { get; set; }
        public virtual DbSet<sysdiagram> sysdiagrams { get; set; }
    }
}