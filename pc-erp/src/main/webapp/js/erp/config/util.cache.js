define({
	root:{},
	put:function(k, v){
		this.root[k]=v;
	},
	get:function(k, fn){
		if(typeof this.root[k] == "undefined"){
			this.root[k] = fn();
		}
		return this.root[k];
	},
	clean:function(){
		this.root={};
	}
});