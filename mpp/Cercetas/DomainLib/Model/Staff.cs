//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace DomainLib.Model
{
    using System;
    using System.Collections.Generic;
    
    public partial class Staff
    {
        public int Id { get; set; }
        public string Username { get; set; }
        public Nullable<int> NrPunct { get; set; }
    
        public virtual Punct Punct { get; set; }
    }
}