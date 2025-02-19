<el-form :inline="true" :model="submitData" class="demo-form-inline" :rules="rules" ref="ruleForm">
    <el-card class="box-card">
        <div slot="header" class="header clearfix">
            <span>news_team</span>
            <el-button v-if="!ischeck && !isFind" class="fr" type="primary" @click="validate('ruleForm')">提交</el-button>
            <el-button v-else class="fr" type="primary" @click="goBack">返回</el-button>
        </div>
             <el-form-item label="n_id" prop="nId">
                 <el-input placeholder="请输入n_id" v-model="formData.nId"></el-input>
             </el-form-item>
    </el-card>
</el-form>